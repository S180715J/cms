package com.newer.cms.pojo;

import java.io.Serializable;

/**
 *  .仅仅是用来接收数据的语种类
 * @author Administrator
 *
 */
public class Languag implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer lid;
	private String lname;
	private String abbreviation;
	private Integer isactivation;
	private User user;
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public Integer getIsactivation() {
		return isactivation;
	}
	public void setIsactivation(Integer isactivation) {
		this.isactivation = isactivation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Languag [lid=" + lid + ", lname=" + lname + ", abbreviation=" + abbreviation + ", isactivation="
				+ isactivation + ", user=" + user + "]";
	}
	
	
}
