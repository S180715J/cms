package com.newer.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.User;
import com.newer.cms.pojo.UserRole;

public interface UserMapper {
	
	/**
	 * .根据用户名和密码 返回用户的权限数值
	 * @param user
	 * @return
	 */
	@Select("SELECT s.rid FROM t_user u LEFT JOIN t_user_role s ON u.`uid`=s.uid WHERE uname=#{uname} AND upassword=#{upassword} AND islogin=1")
	int UserLogin(User user);
	
	/**
	 * .添加用户
	 * @return
	 */
	@Insert("INSERT INTO t_user(uname,upassword,six,phone,email,id) VALUES(#{uname},#{upassword},#{six},#{phone},#{email},#{institution.id})")
	int saveUser(User user); 

	/**
	 * .根据用户id删除用户
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM t_user WHERE uid=#{id}")
	int deleteUser(@Param("id") Integer id);
	
	/**
	 * .更新用户
	 * @param user
	 * @return
	 */
	@Update("UPDATE t_user SET uname=#{uname},upassword=#{upassword},six=#{six},phone=#{phone},email=#{email},id=#{institution.id},islogin=#{islogin} WHERE uid=#{uid}")
	int updateUser(User user);
	
	/**
	 * .查询所有员工
	 * @return
	 */
	@Select("SELECT c.uid AS 'user.uid',c.uname AS 'user.uname',c.six AS 'user.six',\r\n" + 
			"c.namepath AS 'user.institution.namepath',c.islogin AS 'user.islogin',d.rname AS 'role.rname' FROM\r\n" + 
			"(SELECT a.uid,a.uname,a.six,a.namepath,a.islogin,b.rid FROM \r\n" + 
			"(SELECT u.`uid`,u.`uname`,u.`six`,i.`namepath`,u.`islogin` FROM \r\n" + 
			"t_user u LEFT JOIN t_institution i ON u.`id`=i.`id`) a\r\n" + 
			"LEFT JOIN t_user_role b ON a.uid=b.uid ) c LEFT JOIN  t_role d ON c.rid=d.`rid`")
	List<UserRole> queryUserRole();
	
	
}
