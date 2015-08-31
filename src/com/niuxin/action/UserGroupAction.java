package com.niuxin.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.ChatRecord;
import com.niuxin.bean.ShareGroup;
import com.niuxin.bean.UserFriend;
import com.niuxin.bean.UserGroup;
import com.niuxin.service.IChatRecordService;
import com.niuxin.service.IShareGroupService;
import com.niuxin.service.IUserFriendService;
import com.niuxin.service.IUserGroupService;
import com.niuxin.service.IUserService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserGroupAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IUserGroupService userGroupService;
	@Resource
	private IShareGroupService shareGroupService;
	@Resource
	private IChatRecordService chatRecordService;
	@Resource
	private IUserFriendService userFriendService;
	@Resource
	private IUserService userService;

	public void listChatRecord() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		// JSONObject json_data = JSONObject.fromObject(str);
		Integer id = json_data.getInt("id");// 获取用户的ID
		JSONArray jsonarray = new JSONArray();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd  HH:mm");
		/////////////////// 以下是群的聊天记录/////////////////
		List<UserGroup> list = userGroupService.selectByUserid(id);
		List<ShareGroup> groupList = new LinkedList<ShareGroup>();
		for (int i = 0; i < list.size(); i++) {// 获取用户所在的群组信息
			groupList.add(shareGroupService.selectById(list.get(i).getGroupId()));
		}
		for (int i = 0; i < groupList.size(); i++) {// 获取用户所在的群组信息
			ChatRecord ctrecord = chatRecordService.selectLastByGroupId(groupList.get(i).getId());
			if (ctrecord == null)
				continue;
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", groupList.get(i).getId());
			jsonobject.put("name", groupList.get(i).getName());
			String message = ctrecord.getMessage();
			if (message == null || message.equals(""))
				message = "";
			if (message.length() > 10)
				message = message.substring(0, 9) + "…";
			jsonobject.put("lastmes", ctrecord.getMessage());
			String date = format.format(ctrecord.getCreateTime());
			jsonobject.put("time", date);
			jsonobject.put("type", groupList.get(i).getType());
			jsonobject.put("img", groupList.get(i).getImg());
			jsonobject.put("currentNumber", groupList.get(i).getCurrentNumber());
			jsonobject.put("totalNumber", groupList.get(i).getTotalNumber());
			String regEx = "[^0-9]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(groupList.get(i).getEnterGrade());
			String grade = m.replaceAll("").trim();
			jsonobject.put("grade", grade);

			jsonobject.put("chattype", 1);// 1代表是群聊
			jsonobject.put("groupbydate", ctrecord.getCreateTime().getTime());// 根据这个时间进行排序
			jsonarray.add(jsonobject);
		}
		/////////////////// 以下是个人的聊天记录/////////////////
		List<UserFriend> list1 = userFriendService.selectByUserid(id); // 根据用户id
																		// 找到所有的好朋友
		for (int i = 0; i < list1.size(); i++) {
			ChatRecord rec = new ChatRecord();
			rec.setSendUserId(id);
			rec.setReceiveUserId(list1.get(i).getUserFriendId());
			ChatRecord ctrecord = chatRecordService.selectLastByUser(rec);// 找到本人和好友的最后一条聊天记录
			if (ctrecord == null) // 如果和该好友没有聊天记录就继续循环
				continue;
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", list1.get(i).getUserFriendId()); // 好友的id

			jsonobject.put("name", userService.findByUserId(list1.get(i).getUserFriendId()).getUserName());// 获取好友的用户名
			jsonobject.put("img", userService.findByUserId(list1.get(i).getUserFriendId()).getImg());// 获取好友的用户名
			String message = ctrecord.getMessage();
			if (message == null || message.equals(""))
				message = "";
			if (message.length() > 10)
				message = message.substring(0, 9) + "…";
			jsonobject.put("lastmes", ctrecord.getMessage());
			String date = format.format(ctrecord.getCreateTime());
			jsonobject.put("time", date);
			jsonobject.put("chattype", 2);// 2代表是个人聊天
			jsonobject.put("groupbydate", ctrecord.getCreateTime().getTime());// 根据这个时间进行排序
			jsonarray.add(jsonobject);
		}
	
		// 排序
		for (int i = 0; i < jsonarray.size() - 1; i++) {
			for (int j = 0; j < jsonarray.size() - i - 1; j++) {
				JSONObject obj1 = jsonarray.getJSONObject(j);
				JSONObject obj2 = jsonarray.getJSONObject(j);

					Long d1 = Long.valueOf(obj1.get("groupbydate").toString());
					Long d2 = Long.valueOf(obj2.get("groupbydate").toString());

				if (d1.longValue()<d2.longValue()) { // 把小的值交换到后面
					JSONObject temp = jsonarray.getJSONObject(j);
					jsonarray.set(j, jsonarray.get(j + 1));
					jsonarray.set(j + 1, temp);
				}
			}
		}

		String json = jsonarray.toString();
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listTongxunlu() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		// JSONObject json_data = JSONObject.fromObject(str);
		Integer id = json_data.getInt("id");// 获取用户的ID
		JSONArray jsonarray = new JSONArray();

		/////////////////// 以下是个人的聊天记录/////////////////
		List<UserFriend> list1 = userFriendService.selectByUserid(id); // 根据用户id
																		// 找到所有的好朋友
		for (int i = 0; i < list1.size(); i++) {		
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", list1.get(i).getUserFriendId()); // 好友的id
			jsonobject.put("name", userService.findByUserId(list1.get(i).getUserFriendId()).getUserName());// 获取好友的用户名			
			jsonobject.put("img", userService.findByUserId(list1.get(i).getUserFriendId()).getImg());// 群图标
			jsonobject.put("chattype", 2);// 2代表是个人聊天
			jsonarray.add(jsonobject);
		}

	
		/////////////////// 以下是群的聊天记录/////////////////
		List<UserGroup> list = userGroupService.selectByUserid(id);// 根据用户ID查到他所在的组
		List<ShareGroup> groupList = new LinkedList<ShareGroup>(); //
		for (int i = 0; i < list.size(); i++) {// 获取用户所在的群组信息
			groupList.add(shareGroupService.selectById(list.get(i).getGroupId()));
		}
		// Collections.reverse(groupList);倒序
		for (int i = 0; i < groupList.size(); i++) {

			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", groupList.get(i).getId());// 群id
			jsonobject.put("name", groupList.get(i).getName());// 群名称
			jsonobject.put("img", groupList.get(i).getImg());// 群图标
			jsonobject.put("chattype", 1);// 2代表是个人聊天
			jsonarray.add(jsonobject);
		}

		String json = jsonarray.toString();
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
