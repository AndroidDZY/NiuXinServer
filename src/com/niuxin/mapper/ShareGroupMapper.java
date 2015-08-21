package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.ShareGroup;

public interface ShareGroupMapper {

	public Integer insert(ShareGroup group);

	public List<ShareGroup> slectAll();
	
	public ShareGroup selectById(int id);
	
}