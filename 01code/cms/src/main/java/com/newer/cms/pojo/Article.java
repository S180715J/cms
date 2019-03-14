package com.newer.cms.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * .文章类
 * @author Administrator
 *
 */
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer aid;
	private String title;
	private String subhead;
	private String mainbody;
	private String accessoryname ;
	private String accessory ;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date   creattime ;
	private Articletype articletype;
	private Integer status;
	private Integer stick;
	
	public Integer getStick() {
		return stick;
	}
	public void setStick(Integer stick) {
		this.stick = stick;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubhead() {
		return subhead;
	}
	public void setSubhead(String subhead) {
		this.subhead = subhead;
	}
	public String getMainbody() {
		return mainbody;
	}
	public void setMainbody(String mainbody) {
		this.mainbody = mainbody;
	}
	public String getAccessoryname() {
		return accessoryname;
	}
	public void setAccessoryname(String accessoryname) {
		this.accessoryname = accessoryname;
	}
	public String getAccessory() {
		return accessory;
	}
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	public Date getCreattime() {
		return creattime;
	}
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}
	public Articletype getArticletype() {
		return articletype;
	}
	public void setArticletype(Articletype articletype) {
		this.articletype = articletype;
	}
	@Override
	public String toString() {
		return "Article [aid=" + aid + ", title=" + title + ", subhead=" + subhead + ", mainbody=" + mainbody
				+ ", accessoryname=" + accessoryname + ", accessory=" + accessory + ", creattime=" + creattime
				+ ", articletype=" + articletype + "]";
	}
	
	
}
