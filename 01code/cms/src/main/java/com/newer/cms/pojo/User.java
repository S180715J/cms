package com.newer.cms.pojo;

import java.io.Serializable;

/**
 * .用户类
 * @author Administrator
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer uid;
	private String uname;
	private String upassword;
	private Integer six;
	private String phone;
	private String email;
	private Institution institution;
	private Integer islogin;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public Integer getSix() {
		return six;
	}
	public void setSix(Integer six) {
		this.six = six;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public Integer getIslogin() {
		return islogin;
	}
	public void setIslogin(Integer islogin) {
		this.islogin = islogin;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", upassword=" + upassword + ", six=" + six + ", phone="
				+ phone + ", email=" + email + ", islogin=" + islogin + "]";
	}
	
	
}
