package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.Lab;
import com.niuxin.mapper.ArticleMapper;
import com.niuxin.mapper.ChatRecordMapper;
import com.niuxin.mapper.LabMapper;
import com.niuxin.service.IArticleService;
import com.niuxin.service.ILabService;

@Service
public class LabServiceImpl implements ILabService{

	@Resource
    private LabMapper labMapper ;

	@Override
	public List<Lab> selectByCreateId(Integer id) {
		// TODO Auto-generated method stub
		return labMapper.selectByCreateId(id);
	}

	@Override
	public void insert(Lab lab) {
		// TODO Auto-generated method stub
		labMapper.insert(lab);
	}

	@Override
	public void update(Lab lab) {
		// TODO Auto-generated method stub
		labMapper.update(lab);
	}

	@Override
	public void delete(Integer id) {
		labMapper.delete(id);
	}

}