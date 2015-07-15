package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.User;

public interface IUserService {
    
    public int insert(User user);
     
    public int update(User user);
   
    public int delete(String userName);
   
    public List<User> selectAll();
   
    public int countAll();
   
    public User findByUserName(String userName);
}