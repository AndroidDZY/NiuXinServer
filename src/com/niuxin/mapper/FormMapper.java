package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.Form;

public interface FormMapper {

	
	public Integer insert(Form form);

	public List<Form> selectAll();
	
	public List<Form> selectAllReceive();
	
	
	public Form selectById(Integer id);
	
	public void update(Form form);
	
	public void delete(Integer id);
	
	public List<Integer> selectAllSend(Integer id);
	
	public List<Form> selectByFormidList(List<Integer> list);

	public List<Form> selectFormBytime(Integer id);
	public List<Form> selectFormBycontract(Integer id);
	public List<Form> selectFormBysend(Integer id);
}