package com.niuxin.action;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.ShareGroup;
import com.niuxin.bean.UserGroup;
import com.niuxin.service.IShareGroupService;
import com.niuxin.service.IUserGroupService;
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
	
	//1.搜索好友
	
	
	
	public void listGroupById() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		//用json进行解析
		JSONArray jsar =  JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);		
		//JSONObject json_data = JSONObject.fromObject(str);
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
