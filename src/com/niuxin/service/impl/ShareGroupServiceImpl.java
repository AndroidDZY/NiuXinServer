package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.ArticleMapper;
import com.niuxin.mapper.ChatRecordMapper;
import com.niuxin.mapper.LabMapper;
import com.niuxin.mapper.ShareGroupMapper;
import com.niuxin.service.IArticleService;
import com.niuxin.service.ILabService;
import com.niuxin.service.IShareGroupService;

@Service
public class ShareGroupServiceImpl implements IShareGroupService{

	@Resource
    private ShareGroupMapper shareGroupMapper ;

}