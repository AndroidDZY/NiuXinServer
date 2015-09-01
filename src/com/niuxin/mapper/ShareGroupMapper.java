package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.ShareGroup;
import com.niuxin.bean.UserGroup;

public interface ShareGroupMapper {

	public Integer insert(ShareGroup group);

	public List<ShareGroup> slectAll();
	
	public ShareGroup selectById(int id);
	
	public List<ShareGroup> selectByType(String type);
	public List<ShareGroup> selectByisFree(String isfree);
	public List<ShareGroup> selectByShareGroup(ShareGroup userGroup);

	public List<ShareGroup> recommendGroup();
	
	public List<ShareGroup> recommendGroupForYou();

	public List<ShareGroup> recommendGroupHot();

	public List<ShareGroup> recommendGroupLearn();
}