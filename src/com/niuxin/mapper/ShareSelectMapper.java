package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.ShareSelect;


public interface ShareSelectMapper {

	public Integer insert(ShareSelect shareSelect);
	public List<ShareSelect> selectByUserid(int id);

}