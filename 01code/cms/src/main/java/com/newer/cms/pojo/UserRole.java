package com.newer.cms.pojo;

import java.io.Serializable;

/**
 * .角色用户关系类
 * @author Administrator
 *
 */
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
	private Role  role;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserRole [user=" + user + ", role=" + role + "]";
	}
	
}
