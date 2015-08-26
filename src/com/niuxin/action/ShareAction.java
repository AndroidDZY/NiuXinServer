package com.niuxin.action;


import java.io.IOException;
import java.util.Date;
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
import com.niuxin.service.IShareService;
import com.opensymphony.xwork2.ActionSupport;

public class ShareAction extends ActionSupport {
	private static final long serialVersionUID = 2099761683755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IShareService shareService;

	public void selectAll() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		List<Share> list = shareService.selectAll();
		 JSONArray result=JSONArray.fromObject(list);
		 String json = result.toString();
		 System.out.println("json"+json);
		 try {
			response.getWriter().write(json);
			response.getWriter().flush();  
			response.getWriter().close();  
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void insert() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		
	}
}
