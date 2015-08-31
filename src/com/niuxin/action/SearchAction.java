package com.niuxin.action;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.ChatRecord;
import com.niuxin.bean.ShareGroup;
import com.niuxin.bean.User;
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

public class SearchAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;

	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IUserGroupService userGroupService;
	@Resource
	private IShareGroupService shareGroupService;
	@Resource
	private IUserService userService;
	@Resource
	private IChatRecordService chatRecordService;
	@Resource
	private IUserFriendService userFriendService;

	// 1.搜索好友 参数：用户的用户名
	// 群组：根据群组名称，以及群组个股标签
	// 聊天纪录：即聊天内容
	// 收藏：用户收藏的言论和文章（假数据）

	public void serachUser() {// 根据用户的输入条件查找用户
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取用户的id
		String searchText = json_data.getString("searchText");// 获取用户搜索的内容

		List<User> userList = userService.findAllByUserName(searchText);

		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < userList.size(); i++) {// 获取用户所在的群组信息
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", userList.get(i).getId());
			jsonobject.put("name", userList.get(i).getUserName());
			jsonobject.put("img", userList.get(i).getImg());

			jsonarray.add(jsonobject);
		}
		String json = "";
		if (jsonarray.size() != 0)
			json = jsonarray.toString();
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void recommendGroup() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取用户的id

		ShareGroup usgp = new ShareGroup();
		List<ShareGroup> groupList = shareGroupService.recommendGroup();

		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < groupList.size(); i++) {// 获取用户所在的群组信息
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", groupList.get(i).getId());
			jsonobject.put("name", groupList.get(i).getName());
			jsonobject.put("type", groupList.get(i).getType());
			jsonobject.put("currentNumber", groupList.get(i).getCurrentNumber());
			jsonobject.put("totalNumber", groupList.get(i).getTotalNumber());
			jsonobject.put("mark", groupList.get(i).getMark());
			jsonarray.add(jsonobject);
		}
		String json = "";
		if (jsonarray.size() != 0)
			json = jsonarray.toString();
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void serachGroup() {// 根据用户的输入条件查找群组
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取用户的id
		String searchText = json_data.getString("searchText");// 获取用户搜索的内容
		String type = json_data.getString("type");// 股票的类型
		String isfree = json_data.getString("isfree");// 股票是否收费
		String score = json_data.getString("score");// 股票的群组评分
		// String paimingtype = json_data.getString("paimingtype");//股票的排名类型
		// String total_number = json_data.getString("total_number");//讨论组的总人数

		ShareGroup usgp = new ShareGroup();
		usgp.setType(type);
		usgp.setIsfree(isfree);
		usgp.setName(searchText);
		usgp.setMark(searchText);
		List<ShareGroup> groupList = shareGroupService.selectByShareGroup(usgp);

		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < groupList.size(); i++) {// 获取用户所在的群组信息
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", groupList.get(i).getId());
			jsonobject.put("name", groupList.get(i).getName());
			jsonobject.put("type", groupList.get(i).getType());
			jsonobject.put("currentNumber", groupList.get(i).getCurrentNumber());
			jsonobject.put("totalNumber", groupList.get(i).getTotalNumber());
			jsonobject.put("mark", groupList.get(i).getMark());
			jsonarray.add(jsonobject);
		}
		String json = "";
		if (jsonarray.size() != 0)
			json = jsonarray.toString();
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void searchChatRecord() {// 根据用户的输入条件查找聊天记录
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取用户自己的id
		String searchText = json_data.getString("searchText");// 获取用户搜索的内容
		ChatRecord cr = new ChatRecord();
		cr.setSendUserId(id);
		cr.setMessage(searchText);
		List<ChatRecord> recordlist = chatRecordService.selectByChatRecord(cr);

		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < recordlist.size(); i++) {// 获取用户所在的群组信息
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", recordlist.get(i).getId());
			jsonobject.put("message", recordlist.get(i).getMessage());
			jsonobject.put("sendUserId", recordlist.get(i).getSendUserId());
			jsonobject.put("receiveGroupId", recordlist.get(i).getReceiveGroupId());
			jsonobject.put("receiveUserId", recordlist.get(i).getReceiveUserId());
			jsonarray.add(jsonobject);
		}
		String json = "";
		if (jsonarray.size() != 0)
			json = jsonarray.toString();
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void contacts() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		// JSONObject json_data = JSONObject.fromObject(str);
		Integer id = json_data.getInt("id");// 获取用户的ID
		String searchTest = json_data.getString("searchTest");// 获取用户的ID
		JSONArray jsonarray = new JSONArray();

		List<UserFriend> list1 = userFriendService.selectByUserid(id); // 根据用户id
																		// 找到所有的好朋友

		for (int i = 0; i < list1.size(); i++) {
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", list1.get(i).getUserFriendId()); // 好友的id
			String friendname = userService.findByUserId(list1.get(i).getUserFriendId()).getUserName();
			if (!friendname.contains(searchTest)) {
				continue;
			}
			jsonobject.put("name", friendname);// 获取好友的用户名
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
			if (!groupList.get(i).getName().contains(searchTest)) {
				continue;
			}

			jsonobject.put("name", groupList.get(i).getName());// 群名称
			jsonobject.put("img", groupList.get(i).getImg());// 群图标
			jsonobject.put("chattype", 1);// 2代表是个人聊天
			jsonarray.add(jsonobject);
		}
		String json = "";
		if (jsonarray.size() != 0)
			json = jsonarray.toString();
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
