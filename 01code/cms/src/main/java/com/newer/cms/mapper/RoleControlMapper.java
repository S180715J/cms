package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.newer.cms.pojo.Control;
import com.newer.cms.pojo.Role;

/**
 * 
 * 牛耳教育,180801J: <br>
 * 角色权限 数据操作接口 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月23日
 */
public interface RoleControlMapper {

	@Select("SELECT COUNT(*)  FROM t_role")
	int findRoleControlByCount();

	/**
	 * 得到从index 的pageSize条数据
	 * 
	 * @param index
	 *            初始记录数 计算后默认0
	 * @param parseInt
	 *            每页显示记录数 默认10
	 * @return List<Role> 角色权限集合
	 */
	List<Role> findPageRoleControls(@Param("index") int index, @Param("pageSize") int pageSize);

	/**
	 * 查询所有权限
	 * 
	 * @return
	 */
	@Select(" SELECT control_id AS 'controlId',control_name AS 'controlName',control_explain AS 'controlExplain',by1,by2 FROM t_control")
	List<Control> findControls();

	/**
	 * 新增角色
	 * 
	 * @param role
	 *            角色对象
	 * @return 返回影响行数
	 */
	@Insert(" INSERT INTO t_role (rname,duty) VALUES(#{rname},#{duty})")
	int saveRole(Role role);

	/**
	 * 根据角色名查询角色是否已存在
	 * 
	 * @param role
	 *            角色对象
	 * @return 存在返回角色信息，否则返回null
	 */
	@Select("SELECT rid,rname,duty,isusable FROM t_role WHERE rname=#{rname}")
	Role findRole(Role role);

	/**
	 * 根据角色名和角色描述 查询角色信息
	 * 
	 * @param role
	 *            角色对象
	 * @return 成功返回角色信息。否则返回null
	 */
	@Select(" SELECT rid,rname,duty,isusable FROM t_role WHERE rname=#{rname} AND duty=#{duty}")
	Role findRoleByDuty(Role role);

	/**
	 * 添加角色权限
	 * 
	 * @param rid
	 *            角色id
	 * @param controlId
	 *            权限 id <br>
	 *            将角色id 与 权限id 添加至角色权限关系表中
	 * @return
	 */
	@Insert(" INSERT INTO t_role_control(role_id,control_id) VALUE(#{rid},#{controlId})")
	int svaeRoleControl(@Param("rid") Integer rid, @Param("controlId") Integer controlId);

	@Delete("  DELETE  FROM t_role WHERE rid= #{rid}")
	int deleteRole(Integer rid);

	@Delete(" DELETE  FROM t_role_control WHERE role_id= #{rid}")
	int deleteRoleAndControl(Integer rid);

	Role finRoleControlByRid(Integer rid);

	/**
	 * 查询control总记录数 control 权限
	 * 
	 * @return Integer 总记录数
	 */
	@Select("SELECT COUNT(*) FROM t_control")
	Integer findControlCount();

	/**
	 * 查询从index开始的pageSize条记录
	 * 
	 * @param index
	 *            初始index位置
	 * @param pagesize
	 * @return
	 */
	@Select("SELECT  control_id AS 'controlId',control_name AS 'controlName',control_explain AS 'controlExplain',by1,by2 FROM t_control LIMIT #{index},#{pagesize}")
	List<Control> findPageControl(@Param("index") Integer index, @Param("pagesize") Integer pagesize);

}
