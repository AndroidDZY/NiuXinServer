package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.CollectionFormMapper;
import com.niuxin.service.ICollectionFormService;

@Service
public class CollectionFormServiceImpl implements ICollectionFormService{

	@Resource
    private CollectionFormMapper collectionFormMapper ;

}