package com.niuxin.service.impl;



import java.util.List;

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
	public List<Contract> selectAll() {
		// TODO Auto-generated method stub
		return contractMapper.selectAll();
	}


	@Override
	public Contract SelectById(Integer id) {
		// TODO Auto-generated method stub
		return contractMapper.SelectById(id);
	}

	

}