package com.newer.cms.pojo;

import java.io.Serializable;

/**
 * .站点类
 */
public class Site implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer tid;
	private String tname;
	private String tintro;
	private Role role;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTintro() {
		return tintro;
	}
	public void setTintro(String tintro) {
		this.tintro = tintro;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Site [tid=" + tid + ", tname=" + tname + ", tintro=" + tintro + ", role=" + role + "]";
	}
	
}
