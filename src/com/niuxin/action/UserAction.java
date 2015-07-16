package com.niuxin.action;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.niuxin.bean.User;
import com.niuxin.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 209976163083755776L;
	private static Logger logger = Logger.getLogger(UserAction.class);


	@Resource
	private IUserService userService;

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
