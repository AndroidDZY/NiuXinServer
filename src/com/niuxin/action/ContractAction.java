package com.niuxin.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.Contract;
import com.niuxin.service.IContractService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ContractAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
//	private static Logger logger = Logger.getLogger(ContractAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private IContractService contractService;


	// 查找除本人之外的所有用户
	public void selectAll() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		// 用json进行解析
		JSONArray jsar = JSONArray.fromObject(str);
		JSONObject json_data = jsar.getJSONObject(0);
		Integer id = json_data.getInt("id");// 获取用户的id		
		List<Contract> contractList = contractService.selectAll();
		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < contractList.size(); i++) {// 获取除自己之外的所有用户信息			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("id", contractList.get(i).getId());
			jsonobject.put("type", contractList.get(i).getType());		
			jsonarray.add(jsonobject);
		}
		String json = "";
		if (jsonarray.size() != 0)
			json = jsonarray.toString();
		try {
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
