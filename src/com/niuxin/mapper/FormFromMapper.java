package com.niuxin.mapper;

import com.niuxin.bean.FormFrom;

public interface FormFromMapper {

	public Integer insert(FormFrom formFrom);

	public FormFrom selectByUserId(Integer id);

}