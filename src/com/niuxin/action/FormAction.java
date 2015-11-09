package com.niuxin.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.CollectionForm;
import com.niuxin.bean.Form;
import com.niuxin.bean.Lab;
import com.niuxin.bean.SuperForm;
import com.niuxin.bean.Template;
import com.niuxin.service.ICollectionFormService;
import com.niuxin.service.IFormService;
import com.niuxin.service.ITemplateService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FormAction extends ActionSupport {
	private static final long serialVersionUID = 2099761683755776L;
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IFormService formService;
	
	@Resource
	private ITemplateService templateService;
	@Resource
	private ICollectionFormService collectionFormService;


	public void insert() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		SuperForm form = new SuperForm();

		String str = new GetJsonString().getJsonString(request);

		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);

		// 用json进行解析
		JSONObject json_data = jsar.getJSONObject(0);

		Integer type = json_data.getInt("type");// 1代表Form 2代表Template
												// 3代表CollectionForm

		String contract = json_data.getString("contract");
		if (contract != null)
			form.setContract(contract);
		String price = json_data.getString("price");
		if (price != null) {
			BigDecimal bd = new BigDecimal(price);
			form.setPrice(bd);
		}
		Integer handnum = json_data.getInt("handnum");
		if (handnum != null)
			form.setHandnum(handnum);
		String position = json_data.getString("position");
		if (position != null) {
			Double d = new Double(position);
			form.setPosition(d);
		}
		String minnum = json_data.getString("minnum");
		if (minnum != null) {
			Double d = new Double(minnum);
			form.setMinnum(d);
		}
		String maxnum = json_data.getString("maxnum");
		if (maxnum != null) {
			Double d = new Double(maxnum);
			form.setMaxnum(d);
		}
		String remark = json_data.getString("remark");
		if (remark != null)
			form.setRemark(remark);
		String pictureurl = json_data.getString("pictureurl");
		if (pictureurl != null)
			form.setPictureurl(pictureurl);

		String audiourl = json_data.getString("audiourl");
		if (audiourl != null)
			form.setAudiourl(audiourl);

		// 发送的用户分为个人用户和群组 这里用了两个字段 前台要传两个字段		
		String sendtouser = json_data.getString("sendtouser");
		if(sendtouser!=null&&sendtouser!="")
			form.setSendtoUser(Integer.valueOf(sendtouser));	
		String sendtogroup = json_data.getString("sendtogroup");
		if(sendtouser!=null&&sendtouser!="")
			form.setSendtoGroup(Integer.valueOf(sendtogroup));
		form.setCreatetime(new Date());
		form.setUpdatetime(new Date());
		form.setAudioread(0);
		Integer userid = json_data.getInt("userid");
		if (userid != null)
			form.setSendfrom(userid);
		form.setOccupy(0);

		JSONObject jsonObject = new JSONObject();
		try {
			switch (type) {
			case 1: {
				formService.insert((Form)form);
				break;
			}
			case 2: {
				templateService.insert((Template)form);
				break;
			}
			case 3: {
				collectionFormService.insert((CollectionForm)form);
				break;
			}
			default: {
				throw new RuntimeException("没有匹配的类型");
			}
			}
			jsonObject.put("success", true);// 如果成功返回false
		} catch (Exception e) {
			jsonObject.put("success", false);// 如果成功返回false
			e.printStackTrace();
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

	public void update() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		if (jsar != null) {
			for (int i = 0; i < jsar.size(); i++) {
				SuperForm form = new SuperForm();
				JSONObject json_data = jsar.getJSONObject(i);
				Integer id = json_data.getInt("id");// 获取标签的ID
				Integer type = json_data.getInt("type");// 获取标签的ID
				String name = json_data.getString("name");
				form.setUpdatetime(new Date());
				form.setName(name);
				form.setId(id);
				
				switch (type) {// 1代表Form 2代表Template 3代表CollectionForm
				case 1: {
					formService.update((Form)form);
					break;
				}
				case 2: {
					templateService.update((Template)form);
					break;
				}								
				}
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
				JSONObject json_data = jsar.getJSONObject(i);
				Integer id = json_data.getInt("id");// 获取标签的ID
				Integer type = json_data.getInt("type");// 获取标签的ID

				switch (type) {		// 1代表Form 2代表Template  3代表CollectionForm
				case 1: {
					formService.delete(id);
					break;
				}
				case 2: {
					templateService.delete(id);
					break;
				}	
				case 3: {
					collectionFormService.delete(id);
					break;
				}	
				}
		
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
	
	
	
	public void select() {//////这个方法还没写好，还需要修改。
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取表单的id
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
