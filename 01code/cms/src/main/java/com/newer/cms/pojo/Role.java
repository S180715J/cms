package com.newer.cms.pojo;

import java.io.Serializable;

/**
 *   角色类
 * @author Administrator
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer rid;
	private String  rname;
	private String  duty;
	private String encoding;
	
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
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
		return "Role [rid=" + rid + ", rname=" + rname + ", duty=" + duty + ", encoding=" + encoding + "]";
	}
	
	
	
}
