package com.niuxin.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.Follow;
import com.niuxin.bean.Shield;
import com.niuxin.bean.User;
import com.niuxin.bean.UserFriend;
import com.niuxin.service.IFollowService;
import com.niuxin.service.IShieldService;
import com.niuxin.service.IUserFriendService;
import com.niuxin.service.IUserService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IUserService userService;
	@Resource
	private IUserFriendService userFriendService;
	@Resource
	private IFollowService followService;
	@Resource
	private IShieldService shieldService;

	// 收藏好友
	public void followUser() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		int senduserid = json_data.getInt("senduserid");
		int userid = json_data.getInt("userid");
		Follow follow = new Follow();
		follow.setUserId(userid);
		follow.setFollowUserid(senduserid);
		if (senduserid != userid)// 自己不能收藏自己
			followService.insert(follow);

		String json = "";
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 屏蔽好友
	public void shieldUser() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		int senduserid = json_data.getInt("senduserid");
		int userid = json_data.getInt("userid");
		Shield follow = new Shield();
		follow.setUserId(userid);
		follow.setShieldUserid(senduserid);
		if (senduserid != userid)// 自己不能收藏自己
			shieldService.insert(follow);

		String json = "";
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void unfollowUser() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		int senduserid = json_data.getInt("senduserid");
		int userid = json_data.getInt("userid");
		Follow follow = new Follow();
		follow.setUserId(userid);
		follow.setFollowUserid(senduserid);
		followService.delete(follow);

		String json = "";
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//解除屏蔽好友
	public void unshieldUser() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		int senduserid = json_data.getInt("senduserid");
		int userid = json_data.getInt("userid");
		Shield follow = new Shield();
		follow.setUserId(userid);
		follow.setShieldUserid(senduserid);
		shieldService.delete(follow);

		String json = "";
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查找用户的好友
	public void selectByUserid() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取用户的id

		List<UserFriend> userList = userFriendService.selectByUserid(id);
		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < userList.size(); i++) {// 获取除自己之外的所有用户信息
			User user = userService.findByUserId(userList.get(i).getUserFriendId());
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", user.getId());
			jsonobject.put("name", user.getUserName());
			jsonobject.put("img", user.getImg());
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

	// 查找除本人之外的所有用户
	public void serachAllUserExceptSelf() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取用户的id

		List<User> userList = userService.selectAll();
		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < userList.size(); i++) {// 获取除自己之外的所有用户信息
			if (id == userList.get(i).getId())
				continue;
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

	public void countAll() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		// System.out.println("数据库中的记录条数:" + userService.countAll());
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
		if (str != null && !str.equals("")) {
			// 用json进行解析
			JSONArray jsar = JSONArray.fromObject(str);
			List<UserFriend> userfriendlist = null;
			for (int i = 0; i < jsar.size(); i++) {
				UserFriend userSelect = new UserFriend();
				JSONObject json_data = jsar.getJSONObject(i);
				if (i == 0) {
					userfriendlist = userFriendService.selectByUserid(json_data.getInt("selfid"));
				}
				if (userfriendlist != null) {
					int mark = 0;
					for (int j = 0; j < userfriendlist.size(); j++) {
						if (userfriendlist.get(j).getUserFriendId() == json_data.getInt("friendid")) {
							mark = 1;
							break;
						}
					}
					if (mark == 0) {
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
		// System.out.println("开始插入记录？？？？？》》》》》》");
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
