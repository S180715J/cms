package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.newer.cms.pojo.Role;

public interface RoleMapper {
	
	/**
	 * .获得所有角色的集合
	 */

	@Select("SELECT * FROM t_role")
	List<Role> queryRole();
}
