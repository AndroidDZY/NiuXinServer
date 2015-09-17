package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.Template;

public interface TemplateMapper {

	
	public Integer insert(Template form);

	public List<Template> selectAll();
	
	public Template selectById(Integer id);
	
	public void update(Template form);
}