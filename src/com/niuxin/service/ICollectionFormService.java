package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.Form;

public interface ICollectionFormService {

	public Integer insert(Form form);

	public List<Form> selectAll();
	
	public Form selectById(Integer id);
	

}