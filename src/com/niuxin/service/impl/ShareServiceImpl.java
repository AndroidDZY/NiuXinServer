package com.niuxin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.Share;
import com.niuxin.bean.User;
import com.niuxin.mapper.ShareMapper;
import com.niuxin.mapper.UserMapper;
import com.niuxin.service.IShareService;
import com.niuxin.service.IUserService;

@Service
public class ShareServiceImpl implements IShareService{

	@Resource
    private ShareMapper shareDao ;

	@Override
	public List<Share> selectAll() {
		// TODO Auto-generated method stub
		return this.shareDao.selectAll();
	}


	
}