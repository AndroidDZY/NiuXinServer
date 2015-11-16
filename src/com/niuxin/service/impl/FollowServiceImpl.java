package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.Follow;
import com.niuxin.mapper.FollowMapper;
import com.niuxin.service.IFollowService;

@Service
public class FollowServiceImpl implements IFollowService{

	@Resource
    private FollowMapper followMapper ;

	@Override
	public List<Follow> selectByUserId() {
		// TODO Auto-generated method stub
		return followMapper.selectByUserId();
	}

	@Override
	public void insert(Follow follow) {
		// TODO Auto-generated method stub
		followMapper.insert(follow);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		followMapper.delete(id);
	}


}