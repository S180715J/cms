package com.newer.cms.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 角色类
 * 
 * @author Administrator
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer rid;
	private String rname;
	private String duty;

	private Integer isusable;
	private List<Control> controls;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIsusable() {
		return isusable;
	}

	public void setIsusable(Integer isusable) {
		this.isusable = isusable;
	}

	public List<Control> getControls() {
		return controls;
	}

	public void setControls(List<Control> controls) {
		this.controls = controls;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + ", duty=" + duty + ", isusable=" + isusable + ", controls="
				+ controls + "]";
	}

}
