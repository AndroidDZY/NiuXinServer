package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.Template;
import com.niuxin.mapper.TemplateMapper;
import com.niuxin.service.ITemplateService;

@Service
public class TemplateServiceImpl implements ITemplateService{

	@Resource
    private TemplateMapper templateMapper ;

	@Override
	public Integer insert(Template form) {
		
		return templateMapper.insert(form);
	}

	@Override
	public List<Template> selectAll() {
		
		return templateMapper.selectAll();
	}

	@Override
	public Template selectById(Integer id) {
		
		return templateMapper.selectById(id);
	}

	@Override
	public void update(Template form) {
		templateMapper.update(form);
	}

}