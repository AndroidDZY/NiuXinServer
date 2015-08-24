package com.niuxin.action;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.ShareGroup;
import com.niuxin.service.IShareGroupService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
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
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {

			e2.printStackTrace();
		}
		ShareGroup group = new ShareGroup();
		BufferedInputStream in = null;
		String str = "";	
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		str = buffer.toString();
		JSONObject json_data = JSONObject.fromObject(str);
		String name = json_data.getString("name");
		if (name != null)
			group.setName(name);
		String mark = json_data.getString("mark");
		if (mark != null)
			group.setMark(mark);
		String description = json_data.getString("description");
		if (description != null)
			group.setDescription(description);
		String type = json_data.getString("type");
		if (type != null)
			group.setType(type);
		String enterGrade = json_data.getString("enter_grade");
		if (enterGrade != null)
			group.setEnterGrade(enterGrade);
		String isfree = json_data.getString("isfree");
		if (isfree != null)
			group.setIsfree(isfree);
		group.setTotalNumber(50);// 设置群最多人数为50
		group.setCurrentNumber(1);// 群当前人数为1
		group.setCreateTime(new Date());
		
		Integer userid = json_data.getInt("id");//获取穿件的用户id
		group.setCreateuserid(userid);
		Integer result = shareGroupService.insert(group);

		JSONObject jsonObject = new JSONObject();
		if (result != null) {
			try {
				jsonObject.put("success", true);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			try {
				jsonObject.put("success", false);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		String json = jsonObject.toString();

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
