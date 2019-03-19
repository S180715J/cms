package com.newer.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newer.cms.mapper.RoleMapper;
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
}
