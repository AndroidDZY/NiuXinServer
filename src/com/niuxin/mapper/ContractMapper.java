package com.niuxin.mapper;

import java.util.List;

import com.niuxin.bean.Contract;

public interface ContractMapper {

	public Integer insert(Contract contract);

	public Contract selectByUserId(Integer id);

	public List<Contract> selectAll();
	
}