package com.newer.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.pojo.Control;
import com.newer.cms.pojo.Role;
import com.newer.cms.service.RoleService;
import com.newer.cms.utils.JwtTokenUtil;

import io.jsonwebtoken.Claims;

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

	@Value("${jwt.header}")
	private String header;

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

	/**
	 * 更新角色
	 * 
	 * @param role
	 * @return
	 */
	@PutMapping("/role")
	public ResponseEntity<?> updateRole(@RequestBody Role role) {
		System.out.println(role);
		// 调用服务层
		String data = roleSerive.updateRole(role);

		// 判断data是否为空
		if (data == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(data, HttpStatus.OK);
	}

	/**
	 * 得到登录系统中的角色权限
	 * 
	 * @param request
	 * @return 成功返回权限集合 List
	 */
	@GetMapping("/findControl")
	public ResponseEntity<?> findTokenByControl(HttpServletRequest request) {

		// 1.得到请求头
		String token = request.getHeader(header);

		try {
			// 2.解析token
			Claims claims = new JwtTokenUtil().parseJWT(token);

			// 3.得到角色的id
			Integer rid = (Integer) claims.get("role");

			// 4.调用服务层 查询对应的权限
			List<Control> data = roleSerive.findControl(rid);
			if (data == null || data.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<List<Control>>(data, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
