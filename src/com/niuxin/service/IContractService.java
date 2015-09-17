package com.niuxin.service;

import com.niuxin.bean.Contract;

public interface IContractService {

	public Integer insert(Contract contract);

	public Contract selectByUserId(Integer id);

}