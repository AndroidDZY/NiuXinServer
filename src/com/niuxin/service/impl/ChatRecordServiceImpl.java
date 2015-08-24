package com.niuxin.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.ChatRecord;
import com.niuxin.mapper.ChatRecordMapper;

import com.niuxin.service.IChatRecordService;


@Service
public class ChatRecordServiceImpl implements IChatRecordService{

	@Resource
    private ChatRecordMapper chatRecordMapper ;

	@Override
	public void insert(ChatRecord chatRecord) {
		chatRecordMapper.insert(chatRecord);
	}

	@Override
	public List<ChatRecord> selectByGroupId(int id) {
		// TODO Auto-generated method stub
		return chatRecordMapper.selectByGroupId(id);
	}

	@Override
	public List<ChatRecord> selectByUserId(int id) {
		// TODO Auto-generated method stub
		return chatRecordMapper.selectByUserId(id);
	}
   

}