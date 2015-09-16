package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.FormFromMapper;
import com.niuxin.service.IFormFromService;

@Service
public class FormFromServiceImpl implements IFormFromService{

	@Resource
    private FormFromMapper formFormMapper ;

}