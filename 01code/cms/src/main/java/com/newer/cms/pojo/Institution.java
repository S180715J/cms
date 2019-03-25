package com.newer.cms.pojo;

import java.io.Serializable;

/**
 * .机构类
 * @author Administrator
 *
 */
public class Institution implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String iname;
	private Integer fid;
	private String idpath;
	private String namepath;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getIdpath() {
		return idpath;
	}
	public void setIdpath(String idpath) {
		this.idpath = idpath;
	}
	public String getNamepath() {
		return namepath;
	}
	public void setNamepath(String namepath) {
		this.namepath = namepath;
	}
	
}
