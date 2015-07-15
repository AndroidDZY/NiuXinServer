package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.User;


public interface UserMapper {

    public int insert(User user);
     
    public int update(User user);
   
    public int delete(int id);
   
    public List<User> selectAll();
   
    public int countAll();
   
    public User findByUserName(String userName);

}