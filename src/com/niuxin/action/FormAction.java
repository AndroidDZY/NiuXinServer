package com.niuxin.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.Form;
import com.niuxin.bean.FormSendto;
import com.niuxin.service.ICollectionFormService;
import com.niuxin.service.IFormFromService;
import com.niuxin.service.IFormSendtoService;
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
	private IFormFromService formFromService;
	@Resource
	private ITemplateService templateService;
	@Resource
	private ICollectionFormService collectionFormService;
	@Resource
	private IFormSendtoService formSendtoService;

	public void insert() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		Form form = new Form();

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
		FormSendto sendtos = new FormSendto();
		String sendtouser = json_data.getString("sendtouser");
		sendtos.setUserid(sendtouser);
		String sendtogroup = json_data.getString("sendtogroup");
		sendtos.setGroupid(sendtogroup);
		formSendtoService.insert(sendtos);
		if (sendtos.getId() != null)
			form.setSendto(sendtos.getId());
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
				formService.insert(form);
				break;
			}
			case 2: {
				templateService.insert(form);
				break;
			}
			case 3: {
				collectionFormService.insert(form);
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
}
