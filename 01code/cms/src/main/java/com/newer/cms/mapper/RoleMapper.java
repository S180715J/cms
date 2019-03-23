package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.newer.cms.pojo.Role;

public interface RoleMapper {

	/**
	 * .获得所有角色数据的集合
	 */
	@Select("SELECT rid,rname,duty,encoding FROM t_role")
	List<Role> findRoles();
}
