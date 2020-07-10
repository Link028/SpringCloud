package com.link.common.data ;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSet implements java.io.Serializable
{
	protected long count = -1;

	protected List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataSet [count=" + count + ", data=" + data + "]";
	}

	private static final long serialVersionUID = 6111672831344627898L;

}
