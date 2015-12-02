package com.niuxin.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.Contract;
import com.niuxin.bean.Follow;
import com.niuxin.bean.Form;
import com.niuxin.bean.Shield;
import com.niuxin.bean.SuperForm;
import com.niuxin.bean.Template;
import com.niuxin.bean.User;
import com.niuxin.bean.UserGroup;
import com.niuxin.service.IContractService;
import com.niuxin.service.IFollowService;
import com.niuxin.service.IFormService;
import com.niuxin.service.IShieldService;
import com.niuxin.service.ITemplateService;
import com.niuxin.service.IUserGroupService;
import com.niuxin.service.IUserService;
import com.niuxin.util.GetJsonString;
import com.niuxin.util.MyDate;
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

	@Resource
	private IUserService userService;

	@Resource
	private IContractService contractService;

	@Resource
	private IFollowService followService;
	
	@Resource
	private IShieldService shieldService;

	public void insert() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		SuperForm form = null;
		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		// 用json进行解析
		JSONObject json_data = jsar.getJSONObject(0);
		Integer type = json_data.getInt("type");// 1代表Form 2代表Template
												// 3代表CollectionForm
		if (type == 1) {
			form = new Form();
		} else if (type == 2) {
			form = new Template();
		}
		Integer contract = json_data.getInt("contract");
		if (contract != null)
			form.setContract(contract);
		String operation = json_data.getString("operation");
		if (operation != null) {
			form.setOperation(operation);
		}
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

		form.setCollection(0);
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
		Integer sendfrom = json_data.getInt("sendfrom");
		if (sendfrom != null)
			form.setSendfrom(sendfrom);
		form.setOccupy(0);
	//	String name = json_data.getString("name");
		form.setName("报单" + sendfrom + (MyDate.getDateToString()));

		JSONObject jsonObject = new JSONObject();
		try {
			switch (type) {
			case 1: {
				Form f = (Form) form;
				formService.insert(f);
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

				JSONObject json_data = jsar.getJSONObject(i);
				Integer id = json_data.getInt("id");// 获取标签的ID
				Integer type = json_data.getInt("type");// 获取标签的ID
				String name = json_data.getString("name");
				SuperForm form = null;
				if (type == 1) {
					form = new Form();
				}
				if (type == 2) {
					form = new Template();
				}
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

	public void selectById() {// 根据表单的id，找出表单信息
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("formid");// 用户自己的id
		List<Integer> idlist = new LinkedList<Integer>();
		idlist.add(id);
		// 3 idlist，组装所有接收的的报单数据。
		JSONArray jsonarray = getResultJson(idlist, 1);
		// 5 将删选后的json数组转为字符串
		String json = "";
		if (jsonarray != null)
			json = jsonarray.toString();// 返回该用户的所有表单

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void selectTemplateById() {// 根据表单的id，找出表单信息
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("templateid");// 用户自己的id
		List<Integer> idlist = new LinkedList<Integer>();
		idlist.add(id);
		// 3 idlist，组装所有接收的的报单数据。
		JSONArray jsonarray = getResultJson(idlist, 2);
		// 5 将删选后的json数组转为字符串
		String json = "";
		if (jsonarray != null)
			json = jsonarray.toString();// 返回该用户的所有表单

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 按时间顺序查询
	 */
	public void selectFormByTime() {// 按时间顺序查询显示表单
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 用户自己的id
		// 根据发送用户的ID按时间查询。
		List<Form> list = formService.selectFormBytime(id);
		List<Integer> idlist = new LinkedList<Integer>();
		if (null != list) {
			for (int i = 0; i < list.size(); i++) {
				idlist.add(list.get(i).getId());
			}
		}
		JSONArray jsonarray = getResultJson(idlist, 1);
		// 5 将删选后的json数组转为字符串
		String json = "";
		if (jsonarray != null)
			json = jsonarray.toString();// 返回该用户的所有表单

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 按合约类型查询
	 */
	public void selectFormByContract() {// 按合约类型查询显示表单
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 用户自己的id
		// 根据发送用户的ID按时间查询。
		List<Form> list = formService.selectFormBycontract(id);
		List<Integer> idlist = new LinkedList<Integer>();
		if (null != list) {
			for (int i = 0; i < list.size(); i++) {
				idlist.add(list.get(i).getId());
			}
		}
		JSONArray jsonarray = getResultJson(idlist, 1);
		// 5 将删选后的json数组转为字符串
		String json = "";
		if (jsonarray != null)
			json = jsonarray.toString();// 返回该用户的所有表单

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void selectAllBySendId() {// 根据用户的id，找到他的模版
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 用户自己的id

		List<Template> list = templateService.selectAllBySendId(id);
		List<Integer> idlist = new LinkedList<Integer>();
		if (null != list) {
			for (int i = 0; i < list.size(); i++) {
				idlist.add(list.get(i).getId());
			}
		}
		JSONArray jsonarray = getResultJson(idlist, 2);
		// 5 将删选后的json数组转为字符串
		String json = "";
		if (jsonarray != null)
			json = jsonarray.toString();// 返回该用户的所有表单

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 按接收者查询
	 */
	public void selectFormBySend() {// 按接收者查询显示表单
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 用户自己的id
		// 根据发送用户的ID按时间查询。
		List<Form> list = formService.selectFormBysend(id);
		List<Integer> idlist = new LinkedList<Integer>();
		if (null != list) {
			for (int i = 0; i < list.size(); i++) {
				idlist.add(list.get(i).getId());
			}
		}
		JSONArray jsonarray = getResultJson(idlist, 1);
		// 5 将删选后的json数组转为字符串
		String json = "";
		if (jsonarray != null)
			json = jsonarray.toString();// 返回该用户的所有表单

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendAll() {// 根据用户的id，找出他所有发送的表单
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		// 1 用json进行解析接收到的参数 a接收用户的id b报单来源（用户id，群组id 全选为-1 多个以逗号分隔） c合约类型（全选为-1
		// 多个以逗号分隔） d只展示收藏的报单（关闭为0 开启为1）
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("userid");// 用户自己的id

		List<Integer> idlist = new LinkedList<Integer>();
		idlist = formService.selectAllSend(id);// 查找用户自己发送的所有报单id
		// 3 idlist，组装所有接收的的报单数据。
		JSONArray jsonarray = getResultJson(idlist, 1);

		// 5 将删选后的json数组转为字符串
		String json = "";
		if (jsonarray != null)
			json = jsonarray.toString();// 返回该用户的所有表单

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void receiveAll() {// 根据用户的id，找出他所有接收的表单
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		String str = new GetJsonString().getJsonString(request);
		// 1 用json进行解析接收到的参数 a接收用户的id b报单来源（用户id，群组id 全选为-1 多个以逗号分隔） c合约类型（全选为-1
		// 多个以逗号分隔） d只展示收藏的报单（关闭为0 开启为1）
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("userid");// 用户自己的id
		String sendtouserid = json_data.getString("sendtouserid");// 发送给用户的id
																	// 如果报单来源是全选则为-1
		String sendtogroupid = json_data.getString("sendtogroupid");// 发送给群组的id
																	// 如果报单来源是全选则为-1
		String contract = json_data.getString("contract");// 合约类型 如果合约类型是全选则为-1
		Integer collection = json_data.getInt("collection");// 是否只展示收藏 0表示没选择
															// 1表示选择

		// 2 准备数据库查询出来的数据
		List<UserGroup> usergroups = userGroupService.selectByUserid(id);// 根据当前用户，找出他所在的组。
		List<Integer> idlist = getAllReceiveByUserid(id, usergroups);// 根据用户id，查找所有他接收的表单id

		// 3 idlist，组装所有接收的的报单数据。
		JSONArray jsonarray = getResultJson(idlist, 1);

		// 4 根据输入的参数进行删选
		String[] sendtouserids = sendtouserid.split(",");// 获取到所有的发送用户的id
															// 这边可能还有关注者
															// 所以还得继续处理下
															// 但总之这边得到所有的发送用户id
		String[] sendtogroupids = sendtogroupid.split(",");// 获取到所有的发送群组的id
		if (jsonarray != null) {
			for (int i = jsonarray.size() - 1; i >= 0; i--) {// 倒序，这样删除就没有问题了
				JSONObject jo = (JSONObject) jsonarray.get(i);
				// 1 根据发送人
				if (!sendtouserid.equals("-1")) {
					int mark = 0;
					List<String> sendtouseridlist = handleUsers(sendtouserids, id);
					for (String userid : sendtouseridlist) {
						if (!userid.equals(jo.get("sendfrom"))) {
							jsonarray.remove(i);
							mark = 1;
							break;
						}
					}
					if (mark == 1)
						continue;
				}
				// 2 根据发送群组
				if (!sendtogroupid.equals("-1")) {
					int mark = 0;
					for (String groupid : sendtogroupids) {// 前台传过来的群数组
						for (UserGroup group : usergroups) {//// 后台查询出来的群数组
							if (jsonarray.get(i) == null)
								continue;
							if (Integer.valueOf(groupid) == group.getId()) {// 如果当前用的群组和接收用户的群组一直，则保存
								jsonarray.remove(i);
								mark = 1;
								break;
							}
						}
						if (mark == 1)
							continue;
					}
				}
				// 3 根据合约类型
				if (!contract.equals("-1")) {
					String[] contractlist = contract.split(",");
					int mark = 0;
					for (String list : contractlist) {
						if (list.trim().equals(jo.getString("contractid").trim())) {
							mark = 1;
							break;
						}
					}
					if (mark == 0) {
						jsonarray.remove(i);
						continue;
					}
				}
				// 4 根据是否收藏
				if (collection != 0) {// 不等于0 就只有等于1的情况 代表未收藏
					if (collection != jo.getInt("collection")) {
						jsonarray.remove(i);
						continue;
					}
				}
			}

		}
		// 5 将删选后的json数组转为字符串
		String json = "";
		if (jsonarray != null)
			json = jsonarray.toString();// 返回该用户的所有表单

		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> handleUsers(String[] sendtouserids, int userid) {
		List<String> strlist = Arrays.asList(sendtouserids);
		List<Follow> followlist = new LinkedList<Follow>();
		for (int i = 0; i < strlist.size(); i++) {
			if (strlist.get(i).equals("-2")) {
				followlist = followService.selectByUserId(userid);
				strlist.remove(i);
				break;
			}
		}
		if (null != followlist) {
			for (Follow s : followlist) {
				strlist.add(s.getFollowUserid() + "");
			}
		}

		return strlist;
	}

	private JSONArray getResultJson(List<Integer> idlist, int type) { // 根据用报单的id，查询报单结果，并添加必要数据供前台显示
		JSONArray jsonarray = new JSONArray();
		for (Integer formid : idlist) {
			SuperForm form = null;
			if (type == 1) {
				form = formService.selectById(formid);
			} else
				form = templateService.selectById(formid);

			/////////////////////////////////////////先剔除屏蔽的人
//			List<Shield> shields = shieldService.selectByUserId(用户自己的id);
//			int mark=0;
//			for(Shield shieldid:shields){
//				if(shieldid.getShieldUserid()==form.getSendfrom())
//					mark=1;
//			}
//			if(mark==1)
//				continue;
			
			JSONObject jsonobj = JSONObject.fromObject(form);
			User user = userService.findByUserId(form.getSendfrom());
			Contract contract = contractService.SelectById(Integer.valueOf(form.getContract()));
			jsonobj.put("contractid", contract.getId());
			jsonobj.put("contract", contract.getType());
			jsonobj.put("sendfrom", form.getSendfrom());
			jsonobj.put("img", user.getImg());
			jsonobj.put("sendusername", user.getUserName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm EEE");
			String[] dates = sdf.format(form.getCreatetime()).toString().split(" ");
			jsonobj.put("sendusername", user.getUserName());
			jsonobj.put("date", dates[0]);
			jsonobj.put("time", dates[1]);
			jsonobj.put("week", dates[2]);
			
			//合约现价 报价买入价 一个2000 一个1000@master＆无为 
			jsonobj.put("profit", (2000 - 1000) * form.getHandnum() - 10 * form.getHandnum());
			jsonarray.add(jsonobj);
		}

		return jsonarray;
	}

	private List<Integer> getAllReceiveByUserid(Integer id, List<UserGroup> usergroups) {// 私有方法，找出该用户所有接收的表单
		// 1.找出该用户所有接收的表单
		List<Form> formlist = formService.selectAllReceive();// 首先查找出所有表单的接受群组和接受人
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

	public void downloadImg() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		try {
			String id = request.getParameter("id");
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=" + id);
			InputStream inputStream = new FileInputStream("E:\\decode.png");// 此时读取的是资源的绝对路径
			OutputStream outputStream = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}
			outputStream.flush();
			outputStream.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}