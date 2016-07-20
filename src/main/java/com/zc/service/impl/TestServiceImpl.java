package com.zc.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zc.model.User;
import com.zc.service.TestService;


@Service
public class TestServiceImpl implements TestService {

	@Override
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User queryByName(String name) {
		if("".equals(name)||name==null){
			return null;
		}
		
		User user =new User();
		user.setUserName("zhandachang");
		user.setCreateTime(new Timestamp(System.currentTimeMillis())); 
		
		return user;
	}

}
