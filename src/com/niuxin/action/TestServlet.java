package com.niuxin.action;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.niuxin.bean.User;
import com.niuxin.service.IUserService;
import com.niuxin.util.SpringContextUtil;

@WebServlet("/TestServlet/*")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 19898898998L;

	HttpServletResponse response;
	HttpServletRequest request;

	public IUserService userService = (IUserService) SpringContextUtil
			.getBean("userServiceImpl");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.response = response;
		this.request = request;
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");

		String url = request.getRequestURL().toString();
		String[] p = url.split("/");
		String method = p[p.length - 1];
	
		Class clazz = this.getClass();
		Method m = null;
		try {
			m = clazz.getDeclaredMethod(method);
			m.invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	
	
	public void countAll() {
		System.out.println("数据库中的记录条数:" + userService.countAll());
	}

	public void insert() {
		User user = new User();
		user.setCreateTime(new Date());
		user.setPassWord("111111");
		user.setStatus(1);
		user.setUpdateTime(new Date());
		user.setUserName("test");
		userService.insert(user);
	}

	public void selectAll() {
		List<User> list = userService.selectAll();
		for (int i = 0; i < list.size(); i++) {
			User user = list.get(i);
			System.out.println(user.toString());
		}
	}

	public void update() {
		User user = new User();
		user.setId(2);
		user.setCreateTime(new Date());
		user.setPassWord("222");
		user.setStatus(2);
		user.setUpdateTime(new Date());
		user.setUserName("update");
		userService.update(user);
	}

	public void delete() {
		userService.delete(1);
	}

	public void findByName() {
		User user = userService.findByUserName("test");
	}

}
