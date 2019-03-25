package com.newer.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.cms.mapper.RoleMapper;
import com.newer.cms.pojo.Control;
import com.newer.cms.pojo.Role;

/**
 * 
 * 牛耳教育,180801J: 角色服务层 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月18日
 */
@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 得到所有角色信息
	 * 
	 * @return
	 */
	public List<Role> findRoles() {
		// 通过mapper接口查询所有角色信息，直接返回
		return roleMapper.findRoles();
	}

	/**
	 * 更新角色
	 * 
	 * @param role
	 *            角色
	 * @return 成功返回ok否则返回null
	 */
	@Transactional
	public String updateRole(Role role) {
		// 调用数据接口
		int i = roleMapper.updateRole(role);
		return i > 0 ? "ok" : null;
	}

	/**
	 * 通过角色id 得到对应的权限
	 * 
	 * @param rid
	 *            角色id
	 * @return 成功返回权限集合否则返回null
	 */
	public List<Control> findControl(Integer rid) {

		return roleMapper.findControl(rid);
	}
}
