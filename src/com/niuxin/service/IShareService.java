package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.Share;

public interface IShareService {

	 public List<Share> selectAll();

	public Share selectById(Integer shareId);

	public List<Share> selectByShareName(String shareName);
}