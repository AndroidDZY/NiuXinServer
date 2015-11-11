package com.niuxin.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.Form;
import com.niuxin.bean.SuperForm;
import com.niuxin.bean.Template;
import com.niuxin.bean.UserGroup;
import com.niuxin.service.IFormService;
import com.niuxin.service.ITemplateService;
import com.niuxin.service.IUserGroupService;
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
	private IUserGroupService userGroupService;

	@Resource
	private ITemplateService templateService;

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
		if (sendtouser != null && sendtouser != "")
			form.setSendtoUser(sendtouser);
		String sendtogroup = json_data.getString("sendtogroup");
		if (sendtouser != null && sendtouser != "")
			form.setSendtoGroup(sendtogroup);
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
				formService.insert((Form) form);
				break;
			}
			case 2: {
				templateService.insert((Template) form);
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
					formService.update((Form) form);
					break;
				}
				case 2: {
					templateService.update((Template) form);
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

				switch (type) { // 1代表Form 2代表Template
				case 1: {
					formService.delete(id);
					break;
				}
				case 2: {
					templateService.delete(id);
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

	public void selectAll() {// 根据用户的id，找出他所有接收的表单
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取用户的id
		JSONArray jsonarray = new JSONArray();
		String json = "";		
		List<Integer> idlist = getAllByUserid(id);//根据用户id，查找他接收的所有表单id
		for (Integer formid : idlist) {
			Form form = formService.selectById(formid);
			JSONObject jsonobj = JSONObject.fromObject(form);
			jsonarray.add(jsonobj);
		}
		json = jsonarray.toString();//返回该用户的所有表单
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<Integer> getAllByUserid(Integer id){//私有方法，找出该用户所有接收的表单
		// 1.找出该用户所有接收的表单
		List<Form> formlist = formService.selectAllSend();// 首先查找出所有表单的接受群组和接受人
		List<Integer> idlist = new LinkedList<Integer>();
		for (Form forms : formlist) {
			int mark = 0;// 标记位，如果接受人的字符串已经匹配，就不需要在群组里面再找了
			if (forms.getSendtoUser() != null) {// 如果发送的个人不等于空，查看该字符串是否包含本人，字符串以逗号分隔
				String[] sendtousers = forms.getSendtoUser().split(",");
				for (String user : sendtousers) {
					if (id == Integer.valueOf(user)) {
						idlist.add(forms.getId());// 如果接受的个人字符串里面有本人，那就直接记住这个表单的id
						mark = 1;
						break;
					}
				}
			}
			if (forms.getSendtoGroup() != null && mark != 1) {
				String[] groups = forms.getSendtoGroup().split(",");
				List<UserGroup> usergroups = new LinkedList<UserGroup>();
				usergroups = userGroupService.selectByUserid(id);// 根据当前用户，找出他所在的组。
				for (String group : groups) {
					for (UserGroup usergroup : usergroups) {
						if (Integer.valueOf(group) == usergroup.getId()) {// 如果当前用的群组和接收用户的群组一直，则保存
							idlist.add(forms.getId());// 如果接受的个人字符串里面有本人，那就直接记住这个表单的id
							break;
						}
					}
				}
			}
		}
		return idlist;
	}

}
