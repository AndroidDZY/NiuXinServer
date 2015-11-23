package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.Shield;

public interface IShieldService {

	public List<Shield> selectByUserId(int id);

	public void insert(Shield follow);
	
	public void delete(Shield follow);
}