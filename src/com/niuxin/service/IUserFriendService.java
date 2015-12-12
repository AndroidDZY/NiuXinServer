package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.UserFriend;

public interface IUserFriendService {

	public Integer insert(UserFriend userGroup);

	public List<UserFriend> selectByUserid(int userid);

	public List<UserFriend> selectByUseridOrReceiveID(Integer id);
	
	public Boolean isEachFriend(UserFriend uf);//两者是否互为好友
	
}