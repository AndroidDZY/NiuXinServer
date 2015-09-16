package com.niuxin.service;

import com.niuxin.bean.FormFrom;

public interface IFormFromService {

	public Integer insert(FormFrom formFrom);

	public FormFrom selectByUserId(Integer id);

}