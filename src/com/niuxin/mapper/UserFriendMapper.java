package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.UserFriend;

public interface UserFriendMapper {

	public Integer insert(UserFriend userGroup);

	public List<UserFriend> selectByUserid(int userid);

	public List<UserFriend> selectByUseridOrReceiveID(Integer id);

	public List<UserFriend> isEachFriend(UserFriend uf);

}