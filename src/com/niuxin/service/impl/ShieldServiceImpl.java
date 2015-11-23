package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.Shield;
import com.niuxin.mapper.ShieldMapper;
import com.niuxin.service.IShieldService;

@Service
public class ShieldServiceImpl implements IShieldService{

	@Resource
    private ShieldMapper shieldMapper ;

	@Override
	public List<Shield> selectByUserId(int id) {
		// TODO Auto-generated method stub
		return shieldMapper.selectByUserId(id);
	}

	@Override
	public void insert(Shield follow) {
		// TODO Auto-generated method stub
		shieldMapper.insert(follow);
	}

	@Override
	public void delete(Shield follow) {
		// TODO Auto-generated method stub
		shieldMapper.delete(follow);
	}


}