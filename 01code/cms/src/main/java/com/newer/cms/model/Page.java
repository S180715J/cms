package com.newer.cms.model;
/**
 * 分页处理模型
 * @author Administrator
 *
 */

import java.util.List;

import org.springframework.http.HttpStatus;

public class Page<T> {

	@Override
	public String toString() {
		return "Page [data=" + data + ", pageNo=" + pageNo + ", totalPageNo=" + totalPageNo + ", count=" + count
				+ ", pageSize=" + pageSize + "]";
	}

	private String msg;

	private HttpStatus status;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus ok) {
		this.status = ok;
	}

	/**
	 * page中保存的数据集合
	 * 
	 */
	private List<T> data;

	/**
	 * 当前页的页码
	 */
	private int pageNo;

	/**
	 * 总页数
	 */
	private int totalPageNo;

	/**
	 * 总记录数
	 */
	private int count;

	/**
	 * 每页显示多少条数据
	 */
	private int pageSize;

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page(String pageNoStr, int totalRecordNo, int pageSize) {
		this.count = totalRecordNo;
		this.pageSize = pageSize;

		if (this.pageSize == 0) {
			this.pageSize = 10;
		}
		// 计算总页数
		// 通过总记录数和pageSize计算得到总页数
		this.totalPageNo = this.count / this.pageSize + ((this.count % this.pageSize == 0) ? 0 : 1);

		// 修正pageNo
		// 给pageNo一个默认值
		this.pageNo = 1;

		try {
			this.pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			// 如果转换失败，什么都不做，保持默认值
		}

		// pageNo在1-总页数之间修正
		if (this.pageNo > this.totalPageNo) {
			this.pageNo = this.totalPageNo;
		}
		if (this.pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public int getCount() {
		return count;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 是否有下一页
	 * 
	 * @return
	 */
	public boolean isHasNext() {
		return this.pageNo < this.totalPageNo;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 是否有上一页
	 * 
	 * @return
	 */
	public boolean isHasPrev() {
		return this.pageNo > 1;
	}

	/**
	 * 返回上一页的页码
	 * 
	 * @return
	 */
	public int getPrev() {
		if (isHasPrev()) {
			return this.pageNo - 1;
		}
		return this.pageNo;
	}

	/**
	 * 返回下一页的页码
	 * 
	 * @return
	 */
	public int getNext() {
		if (isHasNext()) {
			return this.pageNo + 1;
		}
		return this.pageNo;
	}
}
