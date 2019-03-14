package com.newer.cms.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *  .频道表
 * @author Administrator
 *
 */
public class Channel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cid;
	private String cname;
	private String calias;
	private Integer pid;
	private String cnamepath;
	private String cidpath;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date datetime;
	private Integer tsequence;
	private Language language;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCalias() {
		return calias;
	}
	public void setCalias(String calias) {
		this.calias = calias;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getCnamepath() {
		return cnamepath;
	}
	public void setCnamepath(String cnamepath) {
		this.cnamepath = cnamepath;
	}
	public String getCidpath() {
		return cidpath;
	}
	public void setCidpath(String cidpath) {
		this.cidpath = cidpath;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public Integer getTsequence() {
		return tsequence;
	}
	public void setTsequence(Integer tsequence) {
		this.tsequence = tsequence;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "Channel [cid=" + cid + ", cname=" + cname + ", calias=" + calias + ", pid=" + pid + ", cnamepath="
				+ cnamepath + ", cidpath=" + cidpath + ", datetime=" + datetime + ", tsequence=" + tsequence
				+ ", language=" + language + "]";
	}
	
	
}
