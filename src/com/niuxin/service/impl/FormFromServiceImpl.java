package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.FormFrom;
import com.niuxin.mapper.FormFromMapper;
import com.niuxin.service.IFormFromService;

@Service
public class FormFromServiceImpl implements IFormFromService{

	@Resource
    private FormFromMapper formFormMapper ;

	@Override
	public Integer insert(FormFrom formFrom) {

		return formFormMapper.insert(formFrom);
	}

	@Override
	public FormFrom selectByUserId(Integer id) {

		return formFormMapper.selectByUserId(id);
	}

}