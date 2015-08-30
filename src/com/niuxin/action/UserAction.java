package com.niuxin.action;


import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.Share;
import com.niuxin.bean.ShareSelect;
import com.niuxin.bean.User;
import com.niuxin.bean.UserFriend;
import com.niuxin.service.IUserFriendService;
import com.niuxin.service.IUserService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IUserService userService;
	@Resource
	private IUserFriendService userFriendService;


	public void countAll() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
	//	System.out.println("数据库中的记录条数:" + userService.countAll());
		JSONObject jsonObject = new JSONObject();	
		 try {
			jsonObject.put("username", "huangwuyi");
			jsonObject.put("sex", "男");
		    jsonObject.put("QQ", "413425430");
		    jsonObject.put("Min.score", new Integer(99));
		    jsonObject.put("nickname", "梦中心境");
		} catch (JSONException e) {
			e.printStackTrace();
		}  
		 
		 String json = jsonObject.toString();
		// System.out.println("json"+json);
		 try {
			response.getWriter().write(json);
			response.getWriter().flush();  
			response.getWriter().close();  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertFriend() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		String str = new GetJsonString().getJsonString(request);
		if(str!=null&&!str.equals("")){
			// 用json进行解析
			JSONArray jsar = JSONArray.fromObject(str);
			List<UserFriend> userfriendlist = null;
			for (int i = 0; i < jsar.size(); i++) {
				UserFriend userSelect = new UserFriend();
				JSONObject json_data = jsar.getJSONObject(i);				
				if(i==0){
					userfriendlist = userFriendService.selectByUserid(json_data.getInt("selfid"));
				}	
				if(userfriendlist!=null){
					int mark = 0;
					for(int j = 0;j<userfriendlist.size();j++){						
						if(userfriendlist.get(j).getUserFriendId()==json_data.getInt("friendid")){						
							mark =1;
							break;
						}	
					}
					if(mark==0){
						userSelect.setUserSelfId(json_data.getInt("selfid"));
						userSelect.setUserFriendId(json_data.getInt("friendid"));
						userFriendService.insert(userSelect);
					}
				}
			
			}
		}
		
		String json = "";
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void insert() {
	//	System.out.println("开始插入记录？？？？？》》》》》》");
		User user = new User();
		user.setCreateTime(new Date());
		user.setPassWord("111111");
		user.setStatus(1);
		user.setUpdateTime(new Date());
		user.setUserName("test");
		userService.insert(user);
	}

	

	public void update() {
		User user = new User();
		user.setId(2);
		user.setCreateTime(new Date());
		user.setPassWord("222");
		user.setStatus(2);
		user.setUpdateTime(new Date());
		user.setUserName("update");
		userService.update(user);
	}

	public void delete() {
		userService.delete(1);
	}

	public void findByName() {
		
		User user = userService.findByUserName("test");
	}
	
}
