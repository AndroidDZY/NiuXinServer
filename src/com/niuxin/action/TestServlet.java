package com.niuxin.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.niuxin.bean.User;
import com.niuxin.service.IUserService;
import com.niuxin.service.impl.UserServiceImpl;
import com.niuxin.util.SpringContextUtil;

@WebServlet("/TestServlet/*")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 19898898998L;

	public IUserService userService = (IUserService) SpringContextUtil
			.getBean("userServiceImpl");

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			insert();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
				
	public void countAll() {
		System.out.println("数据库中的记录条数:" + userService.countAll());
	}

	public void insert() {
		System.out.println("开始插入");
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
		}
	}

	public void update() {
		User user = new User();
	
		userService.update(user);
	}

	public void delete() {
		userService.delete("苏若年");
	}

	public void findByName() {
		User user = userService.findByUserName("苏若年");		
	}


}
