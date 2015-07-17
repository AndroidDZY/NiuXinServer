package com.niuxin.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.ChatRecordMapper;

import com.niuxin.service.IChatRecordService;


@Service
public class ChatRecordServiceImpl implements IChatRecordService{

	@Resource
    private ChatRecordMapper chatRecordMapper ;
   

}