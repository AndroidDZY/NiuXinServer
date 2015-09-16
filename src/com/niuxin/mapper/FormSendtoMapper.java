package com.niuxin.mapper;

import com.niuxin.bean.FormSendto;

public interface FormSendtoMapper {

	public Integer insert(FormSendto formSendto);
	
	public FormSendto selectById(Integer id);

}