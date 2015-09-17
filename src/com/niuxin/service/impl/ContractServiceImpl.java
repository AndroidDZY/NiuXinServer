package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.Contract;
import com.niuxin.mapper.ContractMapper;
import com.niuxin.service.IContractService;

@Service
public class ContractServiceImpl implements IContractService{

	@Resource
    private ContractMapper contractMapper ;

	@Override
	public Integer insert(Contract contract) {
	
		return contractMapper.insert(contract);
	}

	@Override
	public Contract selectByUserId(Integer id) {
		
		return contractMapper.selectByUserId(id);
	}

}