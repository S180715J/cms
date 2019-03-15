package com.newer.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.mapper.UserMapper;
import com.newer.cms.pojo.Page;
import com.newer.cms.pojo.UserRole;

@RestController
public class UserController {

	@Autowired
	private UserMapper userMapper;

	/**
	 * .测试能否得到数据（可用）
	 * 
	 * @return
	 */
	@GetMapping("/users")
	public ResponseEntity<?> getUsers() {
		List<UserRole> queryUserRole = userMapper.queryUserRole();
		for (UserRole u : queryUserRole) {
			System.out.println(u.getUser().getUname());
			System.out.println("====================");
			System.out.println(u.getUser().getInstitution().getNamepath());
			System.out.println("====================");
			System.out.println(u.getRole());
		}

		com.newer.cms.pojo.Page<UserRole> page = new com.newer.cms.pojo.Page<>();
		page.setData(queryUserRole);

		return new ResponseEntity<Page<UserRole>>(page, HttpStatus.OK);
	}

}
