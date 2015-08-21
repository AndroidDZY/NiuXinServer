package com.niuxin.action;

import java.io.BufferedInputStream;
import java.text.DateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.ShareGroup;
import com.niuxin.service.IShareGroupService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

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

		String name = request.getParameter("name");
		if (name != null)
			group.setName(name);
		String mark = request.getParameter("mark");
		if (mark != null)
			group.setMark(mark);
		String description = request.getParameter("description");
		if (description != null)
			group.setDescription(description);
		String type = request.getParameter("type");
		if (type != null)
			group.setType(type);
		String enterGrade = request.getParameter("enter_grade");
		if (enterGrade != null)
			group.setEnterGrade(enterGrade);
		String isfree = request.getParameter("isfree");
		if (isfree != null)
			group.setIsfree(isfree);
		String totalNumber = request.getParameter("totalNumber");
		group.setTotalNumber(Integer.valueOf(totalNumber));
		String currentNumber = request.getParameter("currentNumber");
		group.setCurrentNumber(Integer.valueOf(currentNumber));
		group.setCreateTime(new Date());

		Integer result = shareGroupService.insert(group);
		if (result != null) {

		} else {

		}
	}
}
