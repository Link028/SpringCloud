package com.link.common.web;

import java.io.Serializable;


public class PageRequest implements Serializable 
{
	private int start = 0;
	private int size = 10;
	private int num = 0;
	
	private String sortItem;
	private String sortType = "asc";

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSortItem() {
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
