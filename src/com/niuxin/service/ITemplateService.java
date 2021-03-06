package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.Template;

public interface ITemplateService {


	public Integer insert(Template form);

	public List<Template> selectAllBySendId(int id);
	
	public Template selectById(Integer id);

	public void update(Template form);

	public void delete(Integer id);

}