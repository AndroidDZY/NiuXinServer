package com.niuxin.action;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.User;
import com.niuxin.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IUserService userService;

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
