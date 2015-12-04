package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.Form;

public interface IFormService {

	public Integer insert(Form form); //插入表单信息

	public List<Form> selectAll();//查询所有表单信息
	
	public Form selectById(Integer id);//根据表单id 查询

	public void update(Form form);//更新表单

	public void delete(Integer id);//根据表单id，删除表单

	public List<Form> selectAllReceive();//查询所有接受者，接受群的信息。（这个方法不好，还需要再改）

	public List<Integer> selectAllSend(Integer id);//根据用户的id，查找他所有已经发送的报单
	
	public List<Form> selectByFormidList(List<Integer> list);//根据报单id的list集合，查找所有的报单信息。

	public List<Form> selectFormBytime(Integer id);// 根据发送用户的ID按时间查询
	
	public List<Form> selectFormBycontract(Integer id);// 根据发送用户的ID按合约类型查询
	
	public List<Form> selectFormBysend(Integer id);// 根据发送用户的ID按接收者查询

	public void collectionForm(Form form);
}