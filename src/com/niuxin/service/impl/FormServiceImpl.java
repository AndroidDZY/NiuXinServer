package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.Form;
import com.niuxin.mapper.FormMapper;
import com.niuxin.service.IFormService;

@Service
public class FormServiceImpl implements IFormService{

	@Resource
    private FormMapper formMapper ;

	@Override
	public Integer insert(Form form) {
		// TODO Auto-generated method stub
		return formMapper.insert(form);
	}

	@Override
	public List<Form> selectAll() {
		// TODO Auto-generated method stub
		return formMapper.selectAll();
	}

	@Override
	public Form selectById(Integer id) {
		// TODO Auto-generated method stub
		return formMapper.selectById(id);
	}

	@Override
	public void update(Form form) {
		formMapper.update(form);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		formMapper.delete(id);
	}

	@Override
	public List<Form> selectAllReceive() {
		// TODO Auto-generated method stub
		return formMapper.selectAllReceive();
	}

	@Override
	public List<Integer> selectAllSend(Integer id) {
		// TODO Auto-generated method stub
		return formMapper.selectAllSend(id);
	}

	@Override
	public List<Form> selectByFormidList(List<Integer> list) {
		// TODO Auto-generated method stub
		return formMapper.selectByFormidList( list);
	}

	@Override
	public List<Form> selectFormBytime(Integer id) {
		// TODO Auto-generated method stub
		return formMapper.selectFormBytime(id);
	}

	@Override
	public List<Form> selectFormBycontract(Integer id) {
		// TODO Auto-generated method stub
		return formMapper.selectFormBycontract(id);
	}

	@Override
	public List<Form> selectFormBysend(Integer id) {
		// TODO Auto-generated method stub
		return formMapper.selectFormBysend(id);
	}

}