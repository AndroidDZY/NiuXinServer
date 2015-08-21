package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.ShareGroup;

public interface IShareGroupService {

	public Integer insert(ShareGroup group);

	public List<ShareGroup> selectAll();
	
	public ShareGroup selectById(int id);
	

}