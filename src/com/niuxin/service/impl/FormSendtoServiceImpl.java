package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.FormSendtoMapper;
import com.niuxin.service.IFormSendtoService;

@Service
public class FormSendtoServiceImpl implements IFormSendtoService{

	@Resource
    private FormSendtoMapper formSendtoMapper ;

}