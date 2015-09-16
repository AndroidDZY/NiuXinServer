package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.UserInformationMapper;
import com.niuxin.service.IUserInformationService;

@Service
public class UserInformationServiceImpl implements IUserInformationService{

	@Resource
    private UserInformationMapper userInformationMapper ;

}