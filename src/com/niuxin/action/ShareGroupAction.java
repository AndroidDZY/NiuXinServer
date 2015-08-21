package com.niuxin.action;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.niuxin.bean.ShareGroup;
import com.niuxin.service.IShareGroupService;
import com.opensymphony.xwork2.ActionSupport;

public class ShareGroupAction extends ActionSupport {
	private static final long serialVersionUID = 2099761683755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IShareGroupService shareGroupService;

	public void insert() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		ShareGroup group = new ShareGroup();
	//	HttpClient http = new DefaultHttpClient();
		String strResult = EntityUtils.toString(request.getEntity());
		
		Integer result = shareGroupService.insert(group);
		if(result!=null){
			
		}else{
			
		}
	}
}
