package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.Share;


public interface ShareMapper {


    public List<Share> selectAll();

	public Share selectById(Integer id);

	public List<Share> selectByShareName(String number);


}