package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.UserFriend;
import com.niuxin.mapper.UserFriendMapper;
import com.niuxin.service.IUserFriendService;

@Service
public class UserFriendServiceImpl implements IUserFriendService{

	@Resource
    private UserFriendMapper userFriendMapper ;
	
	@Override
	public Integer insert(UserFriend userGroup){
		
		return userFriendMapper.insert(userGroup);
	}
	
	@Override
	public List<UserFriend> selectByUserid(int userid){
		return userFriendMapper.selectByUserid(userid);
	}

}