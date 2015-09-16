package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.Form;

public interface FormMapper {

	
	public Integer insert(Form form);

	public List<Form> selectAll();
	
	public Form selectById(Integer id);
	
	
}