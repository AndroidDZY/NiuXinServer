package com.niuxin.service;

import com.niuxin.bean.FormSendto;

public interface IFormSendtoService {

	public Integer insert(FormSendto formSendto);

	public FormSendto selectById(Integer id);

}