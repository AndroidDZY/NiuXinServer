package com.niuxin.action;



import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.niuxin.bean.User;
import com.niuxin.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 5665914561101769678L;

	@Resource
	IUserService userService;

	
	HttpServletRequest request = ServletActionContext.getRequest();

	private String jsondata = "{success:true}";

	public void login() {
		PrintWriter out = null;
		try {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		out = response.getWriter();
		String username = request.getParameter("username");
		if (username == null || "".equals(username.trim())) {
			jsondata = "{success:false,msg:'用户账号不能为空!'}";
		} else {
			User user = userService.findByUserName(username);
			if (user == null) {
				jsondata = "{success:false,msg:'该用户账号不存在!'}";
			} else {
				String password = request.getParameter("password");
				if (!user.getPassWord().equals(password)) {
					jsondata = "{success:false,msg:'密码不正确'}";
				} else {					
					HttpSession session = request.getSession();
					session.setAttribute("user_id", user.getId());
					session.setAttribute("user_name", user.getUserName());
					jsondata = "{success:true}";
				}
			}
		}
			out.print(jsondata);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
