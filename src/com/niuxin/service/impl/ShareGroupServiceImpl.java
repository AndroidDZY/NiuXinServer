package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.ShareGroup;
import com.niuxin.bean.UserGroup;
import com.niuxin.mapper.ArticleMapper;
import com.niuxin.mapper.ChatRecordMapper;
import com.niuxin.mapper.LabMapper;
import com.niuxin.mapper.ShareGroupMapper;
import com.niuxin.service.IArticleService;
import com.niuxin.service.ILabService;
import com.niuxin.service.IShareGroupService;

@Service
public class ShareGroupServiceImpl implements IShareGroupService{

	@Resource
    private ShareGroupMapper shareGroupMapper ;
	
	
	public Integer insert(ShareGroup group){
		return shareGroupMapper.insert(group);
	}


	@Override
	public List<ShareGroup> selectAll() {
		return shareGroupMapper.slectAll();
	}
	
	@Override
	public ShareGroup selectById(int id){
		return shareGroupMapper.selectById(id);
	}


	@Override
	public List<ShareGroup> selectByType(String type) {
		// TODO Auto-generated method stub
		return shareGroupMapper.selectByType(type);
	}


	@Override
	public List<ShareGroup> selectByisFree(String isfree) {
		// TODO Auto-generated method stub
		return shareGroupMapper.selectByisFree(isfree);
	}


	@Override
	public List<ShareGroup> selectByShareGroup(ShareGroup userGroup) {
		// TODO Auto-generated method stub
		return shareGroupMapper.selectByShareGroup(userGroup);
	}

}