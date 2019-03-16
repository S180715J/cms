package com.newer.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.cms.mapper.UserMapper;
import com.newer.cms.pojo.UserRole;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public List<UserRole> queryUserRole() {
		List<UserRole> queryUserRole = userMapper.queryUserRole();
		return queryUserRole;
	}

}
