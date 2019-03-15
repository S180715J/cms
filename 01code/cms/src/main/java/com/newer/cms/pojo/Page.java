package com.newer.cms.pojo;
/**
 * 测试版 Page
 * 牛耳教育,180801J:
 *            <br>
 * @author Administrator
 * 2019年3月15日
 */

import java.util.List;

public class Page<T> {

	private Integer code = 0;

	private String msg;

	private Integer count = 2000;

	private List<T> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Page [code=" + code + ", msg=" + msg + ", count=" + count + ", data=" + data + "]";
	}

}
