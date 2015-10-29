package com.niuxin.service;

import java.util.List;

import com.niuxin.bean.Contract;

public interface IContractService {

	public Integer insert(Contract contract);
	
	public List<Contract> selectAll();

}