package com.niuxin.action;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.ChatRecord;
import com.niuxin.service.IChatRecordService;
import com.opensymphony.xwork2.ActionSupport;

public class ChatRecordAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IChatRecordService chatRecordService;

	public void insert() {
		ChatRecord chatRecord = new ChatRecord();
		
		chatRecordService.insert(chatRecord);//插入聊天记录
		
	}


}
