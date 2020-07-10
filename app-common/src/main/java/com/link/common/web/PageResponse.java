package com.link.common.web;

import java.io.Serializable;

import com.link.util.StringUtils;

/**
 * json 结果返回对象
 *
 */
public class PageResponse<T> extends RestData<T> implements Serializable
{
	/**
	 * The cur page. 当前页
	 */
	private int	pageNum;
	/**
	 * 每页记录数
	 */
	private int	pageSize;

	/**
	 * 总记录数
	 */
	private int totalRec;

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 排序列
	 */
	private String sortItem;

	/**
	 * 排序的方向 "desc" 或者 "asc".
	 */
	private String sortType = "asc";

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRec() {
		return totalRec;
	}

	public void setTotalRec(int totalRec) {
		this.totalRec = totalRec;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getSortItem() {

		if (StringUtils.isEmpty(sortItem))
		{
			return "";
		}

		return sortItem;
	}

	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	private static final long serialVersionUID = 3142582884351240086L;

}
