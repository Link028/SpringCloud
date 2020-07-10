package com.link.common.web;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * json 结果返回对象
 *
 */
public class RestPage implements Serializable
{
	public static final int MAX_RECORDS = 1000 ;
	private int		size		= 10;
	private int		number		= 1;
	private String	sortName	= null;
	private boolean	sortType	= false;

	public int getSize() {
		if (size <= 0 || size > MAX_RECORDS )
		{
			return MAX_RECORDS ;
		}
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumber() {
		return number - 1;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public boolean isSortType() {
		return sortType;
	}

	public void setSortType(boolean sortType) {
		this.sortType = sortType;
	}

	@Override
	public String toString() {
		return "RestPage [size=" + size + ", number=" + number + ", sortName=" + sortName + ", sortType=" + sortType + "]";
	}

	public PageRequest toPageRequest() {
		
		if (StringUtils.isNotEmpty(sortName))
		{
			Sort sort = new Sort(sortType ? Sort.Direction.DESC : Sort.Direction.ASC, sortName);
			return PageRequest.of(this.getNumber(), this.getSize(), sort);
		}
		return PageRequest.of( this.getNumber(), this.getSize() );
	}

	private static final long serialVersionUID = 3142582884351240086L;

}
