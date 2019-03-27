package com.newer.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newer.cms.mapper.UserMapper;
import com.newer.cms.model.Page;
import com.newer.cms.pojo.User;
import com.newer.cms.pojo.UserRole;

@Transactional
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public List<UserRole> queryUserRole() {
		List<UserRole> queryUserRole = userMapper.queryUserRole();
		return queryUserRole;
	}

	/**
	 * 获取用户信息
	 * 
	 * @param pageNoStr
	 * @param pageSize
	 * @return 带分页效果的用户信息 Page对象
	 */
	public Page<UserRole> getPageByUser(String pageNoStr, Integer pageSize) {
		// 得到用户信息总记录数
		Integer totalUser = userMapper.getTotalUser();

		// 实例化Page对象
		Page<UserRole> page = new Page<>(pageNoStr, totalUser, pageSize);

		// 修正分页初始记录数
		Integer index = (page.getPageNo() - 1) * pageSize;

		List<UserRole> data = userMapper.getPageByUser(index, pageSize);
		page.setData(data);
		return page;
	}

	/**
	 * 用户登录。查询用户信息
	 * 
	 * @param userName
	 * @param password
	 * @return 成功返回用户信息UserRole对象, 否则返回null，
	 */
	public UserRole getUserRole(String userName, String password) {
		// 通过用户名和密码查询用户并返回
		UserRole userRole = userMapper.getUserRoleByUserNameAndPassword(userName, password);
		if (userRole.getRole() == null || userRole.getUser().getIslogin() == 0) {
			return null;
		}
		return userRole;
	}

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @param role
	 * @return String 成功返回"ok",否则返回"error"
	 */
	@Transactional
	public String saveUser(UserRole userRole) {
		// 新增用户 得到影响行数
		int i = userMapper.saveUser(userRole);

		// 判断是否添加成功，成功添加用户角色关系表
		int falg = 0;
		if (i > 0) {
			falg = userMapper.saveUserAndRole(userRole);
		}

		return falg > 0 ? "ok" : "error";
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return String 成功返回"ok",否则返回"error"
	 */
	@Transactional
	public String updateUser(User user) {
		// 更新用户，得到影响行数
		int i = userMapper.updateUser(user);
		return i > 0 ? "ok" : "error";
	}

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 * @return String 成功返回“ok",否则返回"error“
	 */
	@Transactional
	public String deleteUser(Integer id) {
		// 根据id删除用户数据 得到返回的影响函数
		int i = userMapper.deleteUser(id);

		return i > 0 ? "ok" : "error";
	}

}
