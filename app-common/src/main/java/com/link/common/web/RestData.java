package com.link.common.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * json 结果返回对象
 *
 */
public class RestData<T> implements Serializable
{
	private boolean success; 
	private String	code;
	private String	message;
	private long totalElements ;
	private int totalPages ;
	
	private List<T> rows = null ;
	private List<Map<String,Object>> data = null ;
	
	private Map<String,String> map = null ;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	public List<Map<String,Object>> getData() {
	   return data;
	}
	
	public void setData(List<Map<String,Object>> data) {
		this.data = data;
	}
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	private static final long serialVersionUID = 3142582884351240086L;

}
