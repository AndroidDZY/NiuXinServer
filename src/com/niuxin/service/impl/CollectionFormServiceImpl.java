package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.Form;
import com.niuxin.mapper.CollectionFormMapper;
import com.niuxin.service.ICollectionFormService;

@Service
public class CollectionFormServiceImpl implements ICollectionFormService{

	@Resource
    private CollectionFormMapper collectionFormMapper ;

	@Override
	public Integer insert(Form form) {
		// TODO Auto-generated method stub
		return collectionFormMapper.insert(form);
	}

	@Override
	public List<Form> selectAll() {
		// TODO Auto-generated method stub
		return collectionFormMapper.selectAll();
	}

	@Override
	public Form selectById(Integer id) {
		// TODO Auto-generated method stub
		return collectionFormMapper.selectById(id);
	}

}