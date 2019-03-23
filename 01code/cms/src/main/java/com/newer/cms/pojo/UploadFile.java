package com.newer.cms.pojo;

import java.io.Serializable;

public class UploadFile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer code;
	private String msg;
	private String data;
	
	public UploadFile() {
		super();
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public UploadFile(Integer code, String msg, String data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	@Override
	public String toString() {
		return "UploadFile [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	

}
