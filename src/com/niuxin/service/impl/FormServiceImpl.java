package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.FormMapper;
import com.niuxin.service.IFormService;

@Service
public class FormServiceImpl implements IFormService{

	@Resource
    private FormMapper formMapper ;

}