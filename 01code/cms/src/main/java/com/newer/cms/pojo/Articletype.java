package com.newer.cms.pojo;

import java.io.Serializable;

/**
 * .文章类型类
 * @author Administrator
 *
 */
public class Articletype implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer eid;
	private String ename;
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	@Override
	public String toString() {
		return "Articletype [eid=" + eid + ", ename=" + ename + "]";
	}
	
	
}
