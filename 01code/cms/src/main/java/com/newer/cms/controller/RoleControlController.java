package com.newer.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newer.cms.model.Page;
import com.newer.cms.pojo.Control;
import com.newer.cms.pojo.Role;
import com.newer.cms.service.RoleControlService;

/**
 * 
 * 牛耳教育,180801J: 角色权限控制层 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月23日
 */
@RestController
public class RoleControlController {

	@Autowired
	private RoleControlService rCService;

	/**
	 * 获得 角色权限
	 * 
	 * @param pageNoStr
	 *            分页处理必要参数 当前页 默认为1
	 * @param pageSizeStr
	 *            分页处理必要参数 每页显示多少条记录数 ，默认10
	 * @return page对象 角色权限的分页对象
	 */
	@GetMapping("/findPageRoleControl")
	public ResponseEntity<?> findPageRoleControls(
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageSizeStr", required = false, defaultValue = "10") String pageSizeStr) {

		// 调用服务层获取数据
		Page<Role> page = rCService.findPageRoleControls(pageNoStr, pageSizeStr);

		// 判断 page是否存在
		if (page == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		page.setStatus(HttpStatus.OK);
		return new ResponseEntity<Page>(page, HttpStatus.OK);
	}

	/**
	 * 获取所有权限
	 * 
	 * @return list 权限集合
	 */
	@GetMapping("/getControls")
	public ResponseEntity<?> findControls() {

		// 调用服务层
		List<Control> data = rCService.findControls();

		// 判断data是否存在或是否为空
		if (data == null || data.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Control>>(data, HttpStatus.OK);
	}

	/**
	 * 新增角色
	 * 
	 * @param role
	 *            角色对象
	 * @return 成功但会ok 否则返回500错误
	 */
	@PostMapping("/role")
	public ResponseEntity<?> saveRole(@RequestBody Role role) {

		System.out.println(role);
		/*
		 * Gson gson = new Gson(); Role role2 = gson.fromJson(role, Role.class);
		 * System.out.println(role2);
		 */
		// 调用服务层
		String data = rCService.saveRole(role);
		// 判断data是否为空
		if (data == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(data, HttpStatus.OK);
	}

	/**
	 * 根据id删除角色
	 * 
	 * @param rid
	 *            角色id
	 * @return 成功返回OK 否则返回500错误码
	 */
	@DeleteMapping("/role/{rid}")
	public ResponseEntity<?> deleteRole(@PathVariable("rid") Integer rid) {

		// 调用服务层方法
		String data = rCService.deleteRole(rid);

		// 判断data是否为空
		if (data == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(data, HttpStatus.OK);
	}

	/**
	 * 根据角色id得到 角色权限信息
	 * 
	 * @param rid
	 *            角色id
	 * @return Role 成功返回角色对象 否则返回500错误码
	 */
	@GetMapping("/role/{rid}")
	public ResponseEntity<?> findRoleControlByRid(@PathVariable("rid") Integer rid) {

		// 调用服务层获得数据
		Role role = rCService.findRoleControlByRid(rid);

		if (role == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	/**
	 * 获得带分页效果的权限列表
	 * 
	 * @param pageNoStr
	 *            当前页
	 * @param pageSizeStr
	 *            每页显示记录数
	 * @return 成功返回Page对象 否则返回500错误码
	 */
	@GetMapping("/findPageControl")
	public ResponseEntity<?> findPageControls(
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "1") String pageNoStr,
			@RequestParam(value = "pageNoStr", required = false, defaultValue = "10") String pageSizeStr) {
		// 调用服务层
		Integer pagesize = Integer.parseInt(pageSizeStr);

		Page<Control> page = rCService.findPageControl(pageNoStr, pagesize);

		// 判断page是否为空
		if (page == null) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// 给page对象添加状态码
		page.setStatus(HttpStatus.OK);
		return new ResponseEntity<Page<Control>>(page, HttpStatus.OK);
	}

	/**
	 * 通过角色id 得到其拥有的权限
	 * 
	 * @param rid
	 *            角色id
	 * @return 成功返回角色集合 否则返回500错误
	 */
	@GetMapping("/control/{rid}")
	public ResponseEntity<?> findControlByRid(@PathVariable("rid") Integer rid) {

		// 调用服务层
		List<Control> controls = rCService.findControlByRid(rid);
		// 判断List集合是否存在 如果不存在返回500错误码
		if (controls == null || controls.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<List<Control>>(controls, HttpStatus.OK);

	}
}
