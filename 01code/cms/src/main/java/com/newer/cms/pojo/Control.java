package com.newer.cms.pojo;

import java.io.Serializable;

/**
 * 
 * 牛耳教育,180801J: 权限实体类 <br>
 * 
 * @author 朱璐(LuZhu) 2019年3月22日
 */
public class Control implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 权限id
	 */
	private Integer controlId;

	/**
	 * 权限名称
	 */
	private String controlName;

	/**
	 * 权限说明
	 */
	private String controlExplain;

	/**
	 * 备用1
	 */
	private String by1;

	/**
	 * 备用2
	 */
	private String by2;

	public Integer getControlId() {
		return controlId;
	}

	public void setControlId(Integer controlId) {
		this.controlId = controlId;
	}

	public String getControlName() {
		return controlName;
	}

	public void setControlName(String controlName) {
		this.controlName = controlName;
	}

	public String getControlExplain() {
		return controlExplain;
	}

	public void setControlExplain(String controlExplain) {
		this.controlExplain = controlExplain;
	}

	public String getBy1() {
		return by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public String getBy2() {
		return by2;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Control [controlId=" + controlId + ", controlName=" + controlName + ", controlExplain=" + controlExplain
				+ ", by1=" + by1 + ", by2=" + by2 + "]";
	}

}
