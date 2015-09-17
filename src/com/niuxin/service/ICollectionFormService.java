package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.CollectionForm;

public interface ICollectionFormService {

	public Integer insert(CollectionForm form);

	public List<CollectionForm> selectAll();
	
	public CollectionForm selectById(Integer id);
	

}