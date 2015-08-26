package com.niuxin.chat;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.niuxin.bean.ChatRecord;
import com.niuxin.bean.User;
import com.niuxin.bean.UserGroup;
import com.niuxin.service.IChatRecordService;
import com.niuxin.service.IUserGroupService;
import com.niuxin.service.IUserService;
import com.niuxin.util.MyDate;
import com.niuxin.util.TextMessage;
import com.niuxin.util.TranObject;
import com.niuxin.util.TranObjectType;
/*
import com.way.chat.dao.UserDao;
import com.way.chat.dao.impl.UserDaoFactory;
*/
/**
 * 读消息线程和处理方法
 * 
 * @author way
 * 
 */
@Service
public class InputThread implements Runnable {
	private Socket socket = null;// socket对象
	private OutputThread out = null;// 传递进来的写消息线程，因为我们要给用户回复消息啊
	private OutputThreadMap map = null;// 写消息线程缓存器
	private ObjectInputStream ois = null;// 对象输入流
	private boolean isStart = true;// 是否循环读消息
	private IUserService userService = null;
	private IUserGroupService userGroupService = null;
	private IChatRecordService chatRecordService = null;
	
	public InputThread(){//这边需要添加默认构造函数，具体哪边调用还不清楚

	}
	
	public  InputThread(Socket socket, OutputThread out, OutputThreadMap map) {

		isStart = true;// 是否循环读消息
		this.socket = socket;
		this.out = out;
		this.map = map;
		try {
			ois = new ObjectInputStream(socket.getInputStream());// 实例化对象输入流
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

	public void init2(IUserService userService,IUserGroupService userGroupService,IChatRecordService chatRecordService){
		this.userService = userService;
		this.userGroupService = userGroupService;
		this.chatRecordService = chatRecordService;
	}

	public void setStart(boolean isStart) {// 提供接口给外部关闭读消息线程
		this.isStart = isStart;
	}

	@Override
	public void run() {
		try {
			while (isStart) {
				// 读取消息
				readMessage();
			}
			if (ois != null)
				ois.close();
			if (socket != null)
				socket.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	/**
	 * 读消息以及处理消息，抛出异常
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void readMessage() throws IOException, ClassNotFoundException {
		Object readObject = null;
		try{
			 readObject = ois.readObject();// 从流中读取对象
		}catch(Exception e){
			isStart = false;	//这边这样处理，可能会有问题。后续再继续研究吧。。。。。。。。。。		
		}
		

	//	UserDao dao = UserDaoFactory.getInstance();// 通过dao模式管理后台
		if (readObject != null && readObject instanceof TranObject) {
			TranObject read_tranObject = (TranObject) readObject;// 转换成传输对象
			switch (read_tranObject.getType()) {
			case REGISTER:// 如果用户是注册
				User registerUser = (User) read_tranObject.getObject();
				registerUser.setCreateTime(new Date());
				registerUser.setStatus(1);
				registerUser.setIsOnline(0);
				int registerResult = userService.insert(registerUser);
			//	System.out.println(MyDate.getDateCN() + " 新用户注册:"+ registerResult);
				// 给用户回复消息
				TranObject<User> register2TranObject = new TranObject<User>(
						TranObjectType.REGISTER);
				User register2user = new User();
				register2user.setId(registerResult);
				register2user.setStatus(0);
				register2TranObject.setObject(register2user);
				out.setMessage(register2TranObject);
				break;
			case LOGIN:
				User loginUser = (User) read_tranObject.getObject();
				System.out.println(loginUser.getUserName()+"账号名:密码"+loginUser.getPassWord());
				User userQuery = userService.select(loginUser);//查询的用户信息
				TranObject<User> login2Object = new TranObject<User>(
						TranObjectType.LOGIN);
				TranObject<User> onObject = new TranObject<User>(TranObjectType.LOGIN);
				if(userQuery!=null){
					//TranObject<User> onObject = new TranObject<User>(TranObjectType.LOGIN);
					onObject.setObject(userQuery);
				//	for (OutputThread onOut : map.getAll()) {
				//		onOut.setMessage(onObject);// 广播一下用户上线
				//	}
					map.add(userQuery.getId(), out);// 先广播，再把对应用户id的写线程存入map中，以便转发消息时调用
					out.setMessage(onObject);// 同时把登录信息回复给用户
					
					login2Object.setObject(userQuery);// 把好友列表加入回复的对象中  这里暂时只存放自己 没有放好友和群组
				//	System.out.println(MyDate.getDateCN() + " 用户："+ loginUser.getId() + " 上线了");			
				}else{
					onObject.setObject(null);
					out.setMessage(onObject);
					login2Object.setObject(null);
				}
						
				break;
			case LOGOUT:// 如果是退出，更新数据库在线状态，同时群发告诉所有在线用户
		
				User logoutUser = (User) read_tranObject.getObject();
				int offId = logoutUser.getId();
				System.out.println(MyDate.getDateCN() + " 用户：" + offId + " 下线了");
				isStart = false;// 结束自己的读循环
				map.remove(offId);// 从缓存的线程中移除下线的用户
				out.setMessage(null);// 先要设置一个空消息去唤醒写线程
				out.setStart(false);// 再结束写线程循环
				//最好要修改用户下线后的状态
				TranObject<User> offObject = new TranObject<User>(
						TranObjectType.LOGOUT);
				User logout2User = new User();
				logout2User.setId(logoutUser.getId());
				offObject.setObject(logout2User);
				for (OutputThread offOut : map.getAll()) {// 向所有在线的用户广播用户下线消息
					offOut.setMessage(offObject);
				}

				break;
			case MESSAGE:
				// 获取消息中要转发的对象id，然后获取缓存的该对象的写线程
				Integer istogroup = read_tranObject.getIstoGroup();//看是否是给群组发送的,还是给个人发送的。
				ChatRecord chat = new ChatRecord();
				//保存消息
				chat.setSendUserId(read_tranObject.getFromUser());
				if(istogroup==0){//两人之间聊天的情况
					chat.setReceiveUserId(read_tranObject.getToUser());
					chat.setReceiveGroupId(-1);
				}else{
					chat.setReceiveGroupId(read_tranObject.getToUser());
					chat.setReceiveUserId(-1);
				}
					chat.setCreateTime(new Date());
					chat.setMessage(((TextMessage)read_tranObject.getObject()).getMessage());
					chatRecordService.insert(chat);//向数据库保存聊天记录
				//消息转发
				if(istogroup==0){//两人之间聊天的情况
					Integer id2 = read_tranObject.getToUser();
					OutputThread toOut = map.getById(id2);//根据要发送的用户id 找到该用户的线程，然后向该线程写消息
					if (toOut != null) {// 如果用户在线
						TranObject<TextMessage> offText = new TranObject<TextMessage>(
								TranObjectType.MESSAGE);
						offText.setObject((TextMessage)read_tranObject.getObject());
						offText.setFromUser(read_tranObject.getFromUser());
						toOut.setMessage(offText);
					} else {// 如果为空，说明用户已经下线,回复用户
						TextMessage text = new TextMessage();
						text.setMessage("亲！对方不在线哦，您的消息将暂时保存在服务器");
						TranObject<TextMessage> offText = new TranObject<TextMessage>(
								TranObjectType.MESSAGE);
						offText.setObject(text);
						offText.setFromUser(0);
						out.setMessage(offText);
					}
				}else if(istogroup==1){//群聊的情况
					Integer groupID = read_tranObject.getToUser();//获取聊天的群组编号
					List<UserGroup> userList= userGroupService.selectByGroupid(groupID);
					for(int i = 0; i<userList.size();i++){
						OutputThread toOut = map.getById(userList.get(i).getUserId());//根据要发送的用户id 找到该用户的线程，然后向该线程写消息
						if (toOut != null) {// 如果用户在线
							TranObject<TextMessage> offText = new TranObject<TextMessage>(
									TranObjectType.MESSAGE);
							offText.setObject((TextMessage)read_tranObject.getObject());
							offText.setFromUser(read_tranObject.getFromUser());
							toOut.setMessage(offText);
						} 
					}
				}				
				break;
			case REFRESH:
				/*
				List<User> refreshList = dao.refresh(read_tranObject
						.getFromUser());
				TranObject<List<User>> refreshO = new TranObject<List<User>>(
						TranObjectType.REFRESH);
				refreshO.setObject(refreshList);
				out.setMessage(refreshO);
				*/
				break;
			default:
				break;
			}
		}
		
	
	}
}
