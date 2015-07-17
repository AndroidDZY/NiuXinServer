package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.ArticleMapper;
import com.niuxin.mapper.ChatRecordMapper;
import com.niuxin.service.IArticleService;

@Service
public class ArticleServiceImpl implements IArticleService{

	@Resource
    private ArticleMapper articleMapper ;

}