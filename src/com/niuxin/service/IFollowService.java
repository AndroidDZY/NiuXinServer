package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.Follow;

public interface IFollowService {

	public List<Follow> selectByUserId(int id);

	public void insert(Follow follow);
	
	public void delete(int id);
}