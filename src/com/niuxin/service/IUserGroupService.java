package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.UserGroup;


public interface IUserGroupService {

	public Integer insert(UserGroup userGroup);
	public List<UserGroup> selectByUserid(int userid);
	public List<UserGroup> selectByGroupid(int groupid);

}