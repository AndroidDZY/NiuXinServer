package com.niuxin.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.Lab;
import com.niuxin.service.ILabService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LabAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private ILabService labService;

	public void insert() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		// JSONObject json_data = JSONObject.fromObject(str);
		Integer id = json_data.getInt("userid");// 获取用户的ID
		String name = json_data.getString("name");
		Lab lab = new Lab();
		//lab.setCreateId(id);
		lab.setCreateTime(new Date());
		lab.setName(name);
		lab.setCreateId(id);
		labService.insert(lab);
		JSONArray jsonarray = new JSONArray();
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("id", lab.getId());// 标签的id
		jsonarray.add(jsonobject);

		String json = jsonarray.toString();

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (

		IOException e)

		{
			e.printStackTrace();
		}

	}

	public void update() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		if (jsar != null) {
			for (int i = 0; i < jsar.size(); i++) {
				Lab lab = new Lab();
				JSONObject json_data = jsar.getJSONObject(i);
				Integer id = json_data.getInt("id");// 获取标签的ID
				String name = json_data.getString("name");
				lab.setUpdateTime(new Date());
				lab.setName(name);
				lab.setId(id);
				labService.update(lab);
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

	public void delete() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		if (jsar != null) {
			for (int i = 0; i < jsar.size(); i++) {
				Lab lab = new Lab();
				JSONObject json_data = jsar.getJSONObject(i);
				Integer id = json_data.getInt("id");// 获取标签的ID

				labService.delete(id);
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

	public void select() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取用户的ID
		JSONArray jsonarray = new JSONArray();
		String json = "";
		List<Lab> lblist = labService.selectByCreateId(id);// 根据创建者的ID查找

		if (lblist != null) {
			for (int i = 0; i < lblist.size(); i++) {
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("id", lblist.get(i).getId());// 标签的id
				jsonobject.put("name", lblist.get(i).getName());// 标签的名称
				jsonarray.add(jsonobject);
			}
			json = jsonarray.toString();
		}

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
