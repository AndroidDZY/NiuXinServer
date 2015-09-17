package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.CollectionForm;

public interface CollectionFormMapper {


	public Integer insert(CollectionForm form);

	public List<CollectionForm> selectAll();
	
	public CollectionForm selectById(Integer id);
	
	public void delete(Integer id);
}