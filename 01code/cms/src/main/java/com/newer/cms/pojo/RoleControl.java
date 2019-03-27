package com.newer.cms.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 牛耳教育,180801J: 用户权限关系类 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月22日
 */
public class RoleControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色
	 */
	private Role role;

	/**
	 * 权限
	 */
	private List<Control> controls;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Control> getControls() {
		return controls;
	}

	public void setControls(List<Control> controls) {
		this.controls = controls;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RoleControl [role=" + role + ", controls=" + controls + "]";
	}

}
