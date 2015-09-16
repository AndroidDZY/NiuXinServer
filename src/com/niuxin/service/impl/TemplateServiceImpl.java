package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.Form;
import com.niuxin.mapper.TemplateMapper;
import com.niuxin.service.ITemplateService;

@Service
public class TemplateServiceImpl implements ITemplateService{

	@Resource
    private TemplateMapper templateMapper ;

	@Override
	public Integer insert(Form form) {
		// TODO Auto-generated method stub
		return templateMapper.insert(form);
	}

	@Override
	public List<Form> selectAll() {
		// TODO Auto-generated method stub
		return templateMapper.selectAll();
	}

	@Override
	public Form selectById(Integer id) {
		// TODO Auto-generated method stub
		return templateMapper.selectById(id);
	}

}