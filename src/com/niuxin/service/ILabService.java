package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.Lab;

public interface ILabService {

	List<Lab> selectByCreateId(Integer id);

	void insert(Lab lab);

	void update(Lab lab);

  

}