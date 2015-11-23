package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.Shield;

public interface ShieldMapper {


	public List<Shield> selectByUserId(int id);
	public void insert(Shield follow);
	public void delete(Shield follow);
	
}