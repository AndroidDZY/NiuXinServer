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
	public List<ChatRecord> selectByGroupId(Integer id) {
		// TODO Auto-generated method stub
		return chatRecordMapper.selectByGroupId(id);
	}

	@Override
	public List<ChatRecord> selectByUser(ChatRecord cr) {
		// TODO Auto-generated method stub
		return chatRecordMapper.selectByUser(cr);
	}

	@Override
	public ChatRecord selectLastByGroupId(Integer id) {
		// TODO Auto-generated method stub
		return chatRecordMapper.selectLastByGroupId(id);
	}

	@Override
	public ChatRecord selectLastByUser(ChatRecord chatRecord) {
		// TODO Auto-generated method stub
		return chatRecordMapper.selectLastByUser(chatRecord);
	}
  

}