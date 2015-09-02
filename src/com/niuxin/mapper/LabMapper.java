package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.Lab;

public interface LabMapper {

	List<Lab> selectByCreateId(Integer id);

	void insert(Lab lab);

	void update(Lab lab);


}