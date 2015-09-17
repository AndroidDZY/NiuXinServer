package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.CollectionForm;
import com.niuxin.mapper.CollectionFormMapper;
import com.niuxin.service.ICollectionFormService;

@Service
public class CollectionFormServiceImpl implements ICollectionFormService{

	@Resource
    private CollectionFormMapper collectionFormMapper ;

	@Override
	public Integer insert(CollectionForm form) {
		// TODO Auto-generated method stub
		return collectionFormMapper.insert(form);
	}

	@Override
	public List<CollectionForm> selectAll() {
		// TODO Auto-generated method stub
		return collectionFormMapper.selectAll();
	}

	@Override
	public CollectionForm selectById(Integer id) {
		// TODO Auto-generated method stub
		return collectionFormMapper.selectById(id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		collectionFormMapper.delete(id);
	}

}