package com.niuxin.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.Collection;
import com.niuxin.service.ICollectionService;
import com.niuxin.util.GetJsonString;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CollectionAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
	private static Logger logger = Logger.getLogger(CollectionAction.class);
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	@Resource
	private ICollectionService collectionService;

	public void insertFriend() {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		String str = new GetJsonString().getJsonString(request);
		if (str != null && !str.equals("")) {
			// 用json进行解析
			JSONArray jsar = JSONArray.fromObject(str);
		
				JSONObject json_data = jsar.getJSONObject(0);
				int senduserid = json_data.getInt("senduserid");
				int userid = json_data.getInt("userid");
				Collection coll = new Collection();
				
			//	collectionService.insert();		
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

	

}
