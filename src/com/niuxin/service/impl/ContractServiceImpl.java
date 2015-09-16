package com.niuxin.service.impl;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.mapper.ContractMapper;
import com.niuxin.service.IContractService;

@Service
public class ContractServiceImpl implements IContractService{

	@Resource
    private ContractMapper contractMapper ;

}