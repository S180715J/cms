package com.newer.cms.pojo;

import java.io.Serializable;

/**
 * .语种类
 * @author Administrator
 *
 */
public class Language implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer lid;
	private String lname;
	private String abbreviation;
	private Integer isactivation;
	private Site site;
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
	
	
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	@Override
	public String toString() {
		return "Language [lid=" + lid + ", lname=" + lname + ", abbreviation=" + abbreviation + ", isactivation="
				+ isactivation + ", site=" + site + "]";
	}
	
	
}
