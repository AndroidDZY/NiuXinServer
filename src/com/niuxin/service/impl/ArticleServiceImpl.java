package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.ArticleMapper;
import com.niuxin.service.IArticleService;

@Service
public class ArticleServiceImpl implements IArticleService{

	@Resource
    private ArticleMapper articleMapper ;

}