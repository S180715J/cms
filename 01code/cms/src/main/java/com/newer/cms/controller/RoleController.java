package com.newer.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.pojo.Role;
import com.newer.cms.service.RoleService;

/**
 * 角色控制器 牛耳教育,180801J: <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月18日
 */
@RestController
@Transactional
public class RoleController {

	@Autowired
	private RoleService roleSerive;

	/**
	 * 得到所有角色信息
	 * 
	 * @return
	 */
	@GetMapping("/findRole")
	public ResponseEntity<?> findRoles() {

		// 调用服务层的方法获取所有角色信息。
		List<Role> data = roleSerive.findRoles();

		if (data == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Role>>(data, HttpStatus.OK);
	}
}
