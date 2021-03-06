package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.ShareGroup;

public interface IShareGroupService {

	public Integer insert(ShareGroup group);

	public List<ShareGroup> selectAll();
	
	public ShareGroup selectById(int id);
	

	public List<ShareGroup> selectByType(String type);
	public List<ShareGroup> selectByisFree(String isFree);
	public List<ShareGroup> selectByShareGroup(ShareGroup userGroup);

	public List<ShareGroup> recommendGroup();

	public List<ShareGroup> recommendGroupForYou();

	public List<ShareGroup> recommendGroupHot();

	public List<ShareGroup> recommendGroupLearn();
}