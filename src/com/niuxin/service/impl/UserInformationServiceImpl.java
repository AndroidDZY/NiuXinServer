package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.ArticleMapper;
import com.niuxin.mapper.ChatRecordMapper;
import com.niuxin.mapper.LabMapper;
import com.niuxin.mapper.ShareGroupMapper;
import com.niuxin.mapper.ShareMarkMapper;
import com.niuxin.mapper.UserFriendMapper;
import com.niuxin.mapper.UserGroupMapper;
import com.niuxin.mapper.UserInformationMapper;
import com.niuxin.service.IArticleService;
import com.niuxin.service.ILabService;
import com.niuxin.service.IShareGroupService;
import com.niuxin.service.IShareMarkService;
import com.niuxin.service.IUserFriendService;
import com.niuxin.service.IUserGroupService;
import com.niuxin.service.IUserInformationService;

@Service
public class UserInformationServiceImpl implements IUserInformationService{

	@Resource
    private UserInformationMapper userInformationMapper ;

}