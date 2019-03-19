package com.newer.cms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.newer.cms.pojo.User;
import com.newer.cms.pojo.UserRole;

/**
 * 
 * 牛耳教育,180801J: 用户角色数据 <br>
 * 
 * @author Administrator 2019年3月16日
 */
public interface UserMapper {

	/**
	 * .根据用户名和密码 返回用户的权限数值
	 * 
	 * @param user
	 * @return
	 */
	@Select("SELECT s.rid FROM t_user u LEFT JOIN t_user_role s ON u.`uid`=s.uid WHERE uname=#{uname} AND upassword=#{upassword} AND islogin=1")
	int UserLogin(User user);

	/**
	 * .添加用户
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_user(uname,upassword,six,phone,email,id) VALUES(#{user.uname},#{user.upassword},#{user.six},#{user.phone},#{user.email},#{user.institution.id})")
	int saveUser(UserRole userRole);

	/**
	 * .根据用户id删除用户
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM t_user WHERE uid=#{id}")
	int deleteUser(@Param("id") Integer id);

	/**
	 * .更新用户
	 * 
	 * @param user
	 * @return
	 */
	@Update("UPDATE t_user SET uname=#{uname},upassword=#{upassword},six=#{six},phone=#{phone},email=#{email},id=#{institution.id},islogin=#{islogin} WHERE uid=#{uid}")
	int updateUser(User user);

	/**
	 * .查询所有员工
	 * 
	 * @return
	 */
	@Select("SELECT c.uid AS 'user.uid',c.uname AS 'user.uname',c.six AS 'user.six',d.rid AS 'role.rid',\r\n"
			+ "c.namepath AS 'user.institution.namepath',c.islogin AS 'user.islogin',d.rname AS 'role.rname' FROM\r\n"
			+ "(SELECT a.uid,a.uname,a.six,a.namepath,a.islogin,b.rid FROM \r\n"
			+ "(SELECT u.`uid`,u.`uname`,u.`six`,i.`namepath`,u.`islogin` FROM \r\n"
			+ "t_user u LEFT JOIN t_institution i ON u.`id`=i.`id`) a\r\n"
			+ "LEFT JOIN t_user_role b ON a.uid=b.uid ) c LEFT JOIN  t_role d ON c.rid=d.`rid`")
	List<UserRole> queryUserRole();

	/**
	 * 查询用户信息数据总记录数
	 * 
	 * @return integer 用户数据总记录数
	 */
	@Select("SELECT COUNT(*) FROM t_user")
	Integer getTotalUser();

	/**
	 * 获取从index开始，的pageSize条记录
	 * 
	 * @param index
	 * @param pageSize
	 * @return 用户数据集合
	 */
	@Select("SELECT c.uid AS 'user.uid',c.uname AS 'user.uname',c.six AS 'user.six',c.upassword AS 'user.upassword',d.rid AS 'role.rid',c.email AS 'user.email',\r\n"
			+ "	c.id AS 'user.institution.id', c.iname AS 'user.institution.iname',c.idpath AS 'user.institution.idpath',c.fid AS 'user.institution.fid',\r\n"
			+ "	c.phone AS 'user.phone',c.namepath AS 'user.institution.namepath',c.islogin AS 'user.islogin',d.rname AS 'role.rname' FROM\r\n"
			+ "	(SELECT a.`uid`,a.fid,b.`rid`,a.uname,a.upassword,a.six,a.phone,a.email,a.id,a.islogin\r\n"
			+ "	,a.iname,a.namepath,a.idpath FROM \r\n"
			+ "	(SELECT u.`uid`,u.`uname`,u.`upassword`,u.`six`,u.`phone`,u.`email`\r\n"
			+ "	,u.`id`,u.`islogin`,i.`iname`,i.`fid`,i.`namepath`,i.`idpath` FROM \r\n"
			+ "	t_user u LEFT JOIN t_institution i ON u.`id`=i.`id`) a\r\n"
			+ "	LEFT JOIN t_user_role b ON a.uid=b.uid ) c LEFT JOIN  t_role d ON c.rid=d.`rid`\r\n"
			+ "order by c.uid    desc\r\n" + "						LIMIT #{index},#{pageSize}")
	List<UserRole> getPageByUser(@Param("index") Integer index, @Param("pageSize") Integer pageSize);

	/**
	 * 通过用户名与密码查询用户
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@Select("SELECT c.uid AS 'user.uid',c.uname AS 'user.uname',c.six AS 'user.six',c.upassword AS 'user.password',d.rid AS 'role.rid',\r\n"
			+ "			c.namepath AS 'user.institution.namepath',c.islogin AS 'user.islogin',d.rname AS 'role.rname' FROM\r\n"
			+ "			(SELECT a.uid,a.uname,a.upassword,a.six,a.namepath,a.islogin,b.rid FROM \r\n"
			+ "			(SELECT u.`uid`,u.`uname`,u.upassword,u.`six`,i.`namepath`,u.`islogin` FROM \r\n"
			+ "			t_user u LEFT JOIN t_institution i ON u.`id`=i.`id`) a\r\n"
			+ "			LEFT JOIN t_user_role b ON a.uid=b.uid ) c LEFT JOIN  t_role d ON c.rid=d.`rid`\r\n"
			+ "			WHERE c.uname=#{userName} AND c.upassword=#{password};")
	UserRole getUserRoleByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

	void updateUsers(Map<String, Object> param);

	@Insert("INSERT INTO t_user_role(rid,uid)VALUES(#{role.rid},(SELECT uid FROM t_user WHERE uname=#{user.uname} AND upassword=#{user.upassword} AND phone=#{user.phone} ))")
	int saveUserAndRole(UserRole userRole);

}
