package com.carol.admin.base;

import java.util.List;

/**
 * 封装分页结果集
 * 
 * @author chris
 *
 */
public class PageTable {

	private Integer code;
	private String  msg;
	/**
	 * 总数
	 */
	private Long count;
	/**
	 * 结果集
	 */
	private List data;

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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
}
