package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.UserGroup;


public interface UserGroupMapper {

	public Integer insert(UserGroup userGroup);
	public List<UserGroup> selectByUserid(int id);

}