package com.niuxin.action;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import com.niuxin.bean.ShareGroup;
import com.niuxin.bean.User;
import com.niuxin.bean.UserGroup;
import com.niuxin.service.IShareGroupService;
import com.niuxin.service.IUserGroupService;
import com.niuxin.service.IUserService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

public class UserGroupAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IUserGroupService userGroupService;
	@Resource
	private IShareGroupService shareGroupService;
	
	public void listGroupById() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		JSONObject json_data = JSONObject.fromObject(str);
		Integer id = json_data.getInt("id");//获取用户的ID
		List<UserGroup> list = userGroupService.selectByUserid(id); //建创建群组的数据保存到数据库，返回该条数据的ID；
		List<ShareGroup> groupList = new LinkedList<ShareGroup>();
		for(int i =0 ;i<list.size();i++){//获取用户所在的群组信息
			groupList.add(shareGroupService.selectById(list.get(i).getGroupId()));
		}
		//还需要获取用户的聊天信息 包括最后一个发言的用户名和他的发言记录  还有发言时间
		//这里暂时先构造一些假数据
		String lastmes = "汪总：今天又要涨停";
		String time = "13:20";
		/////////////////////////////////////////
		JSONArray jsonarray = new JSONArray();
		for(int i =0 ;i<groupList.size();i++){//获取用户所在的群组信息
			 JSONObject jsonobject = new JSONObject();
			 jsonobject.put("id", groupList.get(i).getId());	
			 jsonobject.put("name", groupList.get(i).getName());	
			 jsonobject.put("lastmes", lastmes);	
			 jsonobject.put("time", time);	
			 jsonobject.put("type", groupList.get(i).getType());	
			 jsonobject.put("currentNumber",  groupList.get(i).getCurrentNumber());	
			 jsonobject.put("totalNumber", groupList.get(i).getTotalNumber());		
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
