package com.newer.cms.pojo;

import java.io.Serializable;

import javax.validation.constraints.Size;

/**
 * . 用户站点关系表
 * @author Administrator
 *
 */
public class SiteUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Size size;
	private User user;
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "SiteUser [size=" + size + ", user=" + user + "]";
	}
	
}
