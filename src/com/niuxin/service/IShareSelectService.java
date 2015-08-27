package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.ShareSelect;


public interface IShareSelectService {


	public Integer insert(ShareSelect shareSelect);
	public List<ShareSelect> selectByUserid(int id);
	public void delete(ShareSelect id);
	
}