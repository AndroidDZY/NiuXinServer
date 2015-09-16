package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.UserGroup;
import com.niuxin.mapper.UserGroupMapper;
import com.niuxin.service.IUserGroupService;

@Service
public class UserGroupServiceImpl implements IUserGroupService{

	@Resource
    private UserGroupMapper userGroupMapper ;

	@Override
	public Integer insert(UserGroup userGroup) {
		// TODO Auto-generated method stub
		return userGroupMapper.insert(userGroup);
	}

	@Override
	public List<UserGroup> selectByUserid(int id) {
		// TODO Auto-generated method stub
		return userGroupMapper.selectByUserid(id);
	}

	@Override
	public List<UserGroup> selectByGroupid(int groupid) {
		// TODO Auto-generated method stub
		return userGroupMapper.selectByGroupid(groupid);
	}

	

}