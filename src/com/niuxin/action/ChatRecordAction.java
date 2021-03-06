package com.niuxin.action;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.ChatRecord;
import com.niuxin.bean.User;
import com.niuxin.service.IChatRecordService;
import com.niuxin.service.IUserService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ChatRecordAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
//	private static Logger logger = Logger.getLogger(UserAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IChatRecordService chatRecordService;
	@Resource
	private IUserService userService;

	
	public void select(){//根据用户组来查询
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		String requestStr = new GetJsonString().getJsonString(request);
	
		JSONArray jsar =  JSONArray.fromObject(requestStr);
		
		//用json进行解析
		JSONObject data = jsar.getJSONObject(0);
		String sendUserId = data.getString("sendUserId");//发送消息的用户
		String groupId = data.getString("groupId");//群组id
		String sendtoUserId = data.getString("sendtoUserId");//接受消息的用户
		List<ChatRecord> list = null;
		if(!groupId.equals("-1")){
			list = chatRecordService.selectByGroupId(Integer.valueOf(groupId));
		}else {
			ChatRecord cr = new ChatRecord();
			cr.setSendUserId(Integer.valueOf(sendUserId));
			cr.setReceiveUserId(Integer.valueOf(sendtoUserId));
			list = chatRecordService.selectByUser(cr);
		}
		String json = "";
		JSONArray jsa = new JSONArray();
		if(list!=null)
		{
			JSONObject oj = null;
			for(ChatRecord lt:list){
				User user = userService.findByUserId(lt.getSendUserId());
				oj = JSONObject.fromObject(lt);
				oj.put("sendUsername", user.getUserName());
				oj.put("img", user.getImg());
				jsa.add(oj);
			}			
			json = jsa.toString();	
		}else{
			JSONObject js = new JSONObject();
			js.put("hasdata", "nodata");	
			json = js.toString();
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
