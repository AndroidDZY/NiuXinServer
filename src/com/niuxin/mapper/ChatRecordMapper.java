package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.ChatRecord;


public interface ChatRecordMapper {

	public void insert(ChatRecord chatRecord);
	public List<ChatRecord> selectByGroupId(Integer receiveGroupId);//根据用户组来查询
	public List<ChatRecord> selectByUser(ChatRecord chatRecord);//根据发送或者接收的用户来查询
	public ChatRecord selectLastByGroupId(Integer receiveGroupId);
	public ChatRecord selectLastByUser(ChatRecord chatRecord);
	public List<ChatRecord> selectByChatRecord(ChatRecord cr);

}