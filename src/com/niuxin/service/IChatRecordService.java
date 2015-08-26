package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.ChatRecord;

public interface IChatRecordService {

	public void insert(ChatRecord chatRecord);
	public List<ChatRecord> selectByGroupId(Integer id);//根据用户组来查询
	public List<ChatRecord> selectByUser(ChatRecord chatRecord);//根据发送或者接收的用户来查询

}