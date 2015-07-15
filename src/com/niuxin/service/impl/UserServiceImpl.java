package com.niuxin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.User;
import com.niuxin.mapper.UserMapper;
import com.niuxin.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Resource
    private UserMapper userDao ;
   
	 @Override
    public int countAll() {
        return this.userDao.countAll();
    }
	 @Override
    public int delete(String userName) {
        return this.userDao.delete(userName);
    }
	 @Override
    public User findByUserName(String userName) {
        return this.userDao.findByUserName(userName);
    }
	 @Override
    public int insert(User user) {
        return this.userDao.insert(user);
    }
	 @Override
    public List<User> selectAll() {
        return this.userDao.selectAll();
    }
	 @Override
    public int update(User user) {
         return this.userDao.update(user);
    }

}