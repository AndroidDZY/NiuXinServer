package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.Follow;

public interface FollowMapper {


	public List<Follow> selectByUserId(int id);
	public void insert(Follow follow);
	public void delete(int id);
	
}