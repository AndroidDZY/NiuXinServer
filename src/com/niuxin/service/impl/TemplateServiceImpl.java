package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.TemplateMapper;
import com.niuxin.service.ITemplateService;

@Service
public class TemplateServiceImpl implements ITemplateService{

	@Resource
    private TemplateMapper templateMapper ;

}