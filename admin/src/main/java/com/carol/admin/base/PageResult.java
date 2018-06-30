package com.carol.admin.base;

import java.util.List;

/**
 * 封装分页结果集
 * 
 * @author chris
 *
 */
public class PageResult {

	private Integer page;// 要查找第几页
	private Integer pageSize;// 每页显示多少条
	private Long totalPage = 0L;// 总页数
	private Long total;// 总记录数
	private List rows;// 结果集

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalPage() {
		if (this.getPageSize() != null) {
			return (this.getTotal() + this.getPageSize() - 1) / this.getPageSize();// 总页数的算法
		} else {
			return totalPage;
		}
	}


	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
