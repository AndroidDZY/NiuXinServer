package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.ArticleMapper;
import com.niuxin.mapper.ChatRecordMapper;
import com.niuxin.mapper.CollectionMapper;
import com.niuxin.service.IArticleService;
import com.niuxin.service.ICollectionService;

@Service
public class CollectionServiceImpl implements ICollectionService{

	@Resource
    private CollectionMapper collectionMapper ;

}