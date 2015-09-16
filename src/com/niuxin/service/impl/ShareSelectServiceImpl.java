package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.ShareSelect;
import com.niuxin.mapper.ShareSelectMapper;
import com.niuxin.service.IShareSelectService;

@Service
public class ShareSelectServiceImpl implements IShareSelectService{

	@Resource
    private ShareSelectMapper shareSelectMapper ;




	@Override
	public Integer insert(ShareSelect shareSelect) {
		// TODO Auto-generated method stub
		return shareSelectMapper.insert(shareSelect);
	}


	@Override
	public List<ShareSelect> selectByUserid(int id) {
		// TODO Auto-generated method stub
		return shareSelectMapper.selectByUserid(id);
	}


	@Override
	public void delete(ShareSelect id) {
		// TODO Auto-generated method stub
		shareSelectMapper.delete(id);
	}

}