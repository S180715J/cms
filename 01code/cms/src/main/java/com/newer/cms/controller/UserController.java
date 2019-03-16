package com.newer.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.pojo.UserRole;
import com.newer.cms.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 *   .测试能否得到数据（可用）  
	 * @return
	 */
	@GetMapping("/users")
	public String getUsers() {
		List<UserRole> queryUserRole = userService.queryUserRole();
		for (UserRole u : queryUserRole) {
			System.out.println(u.getUser().getUname());
			System.out.println("====================");
			System.out.println(u.getUser().getInstitution().getNamepath());
			System.out.println("====================");
			System.out.println(u.getRole());
		}
		return "ok";
	}
	
}
