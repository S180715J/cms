package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.Control;
import com.newer.cms.pojo.Role;

public interface RoleMapper {

	/**
	 * .获得所有角色数据的集合
	 */
	@Select("SELECT rid,rname,duty FROM t_role")
	List<Role> findRoles();

	@Update("  UPDATE t_role SET rname=#{rname}, duty=#{duty},isusable=#{isusable} WHERE rid=#{rid}")
	int updateRole(Role role);

	/**
	 * 根据角色id查询角色权限
	 * 
	 * @param rid
	 * @return
	 */
	@Select("SELECT c.`control_id`,c.`control_name` FROM t_role_control t\r\n" + "LEFT JOIN t_control c\r\n"
			+ "ON c.`control_id` = t.`control_id`\r\n" + "WHERE t.`role_id` =#{rid} ")
	List<Control> findControl(Integer rid);
}
