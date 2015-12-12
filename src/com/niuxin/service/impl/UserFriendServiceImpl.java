package com.niuxin.service.impl;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.niuxin.bean.UserFriend;
import com.niuxin.mapper.UserFriendMapper;
import com.niuxin.service.IUserFriendService;

@Service
public class UserFriendServiceImpl implements IUserFriendService{

	@Resource
    private UserFriendMapper userFriendMapper ;
	
	@Override
	public Integer insert(UserFriend userGroup){
		
		return userFriendMapper.insert(userGroup);
	}
	
	@Override
	public List<UserFriend> selectByUserid(int userid){
		return userFriendMapper.selectByUserid(userid);
	}

	@Override
	public List<UserFriend> selectByUseridOrReceiveID(Integer id) {
		// TODO Auto-generated method stub
		return userFriendMapper.selectByUseridOrReceiveID(id);
	}

	@Override
	public Boolean isEachFriend(UserFriend uf) {
		// TODO Auto-generated method stub
		List<UserFriend> u = userFriendMapper.isEachFriend(uf);
		if(u!=null){
			if(u.size()==2){
				return true;
			}
			
		}
		return false;
	}

}