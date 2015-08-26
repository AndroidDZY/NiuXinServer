package com.niuxin.action;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.Share;
import com.niuxin.bean.ShareGroup;
import com.niuxin.bean.ShareSelect;
import com.niuxin.bean.UserGroup;
import com.niuxin.service.IShareSelectService;
import com.niuxin.service.IShareService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class ShareAction extends ActionSupport {
	private static final long serialVersionUID = 2099761683755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IShareService shareService;
	@Resource
	private IShareSelectService shareSelectService;

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
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {

			e2.printStackTrace();
		}
		
		String str = new GetJsonString().getJsonString(request);
			//用json进行解析
		JSONArray jsar =  JSONArray.fromObject(str);

		for(int i =0;i<jsar.size();i++){
			ShareSelect shareSelect = new ShareSelect();	
			JSONObject json_data = jsar.getJSONObject(i);
			shareSelect.setShareId(json_data.getInt("ShareId"));
			shareSelect.setShareId(json_data.getInt("UserId"));
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
}
