package com.link.common.data ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
 
public class Record implements Serializable
{ 
	/** 操作类型 */
	private String table = null;
	
	/** 操作类型 */
	private String oper = null;

	/** 字段及值集合 */
	protected Map<String, String> data = null;

	//{"$1":"CUST_INFO","$2":"I","f1":"v1","f2":"v3","f3":"v3","f4":"v4"}
	public Record()
	{
		this.data = new HashMap<String, String>();
	}

	public Record( String table , String oper )
	{
		this.table = table ;
		this.oper = oper;
		this.data = new HashMap<String, String>();
	}
	
	public Record( Map<String, String> data)
	{   
		this.data = new HashMap<String, String>();
		this.data.putAll( data ); 
	}
	
	public Record(String table , String oper , Map<String, String> data)
	{
		this.table = table ;
		this.oper = oper;
		this.data = new HashMap<String, String>();
		this.data.putAll( data ); 
	}
	
	public Record(String json)
	{
		Record rec = RecordReader.fromJson(json) ;
		this.table = rec.getTable() ;
		this.oper = rec.getOper();
		this.data = rec.getData() ;
	}
	  
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table ;
	}
	
	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	
	public void set(String key, String val) {
		this.data.put(key, val);
	}
 
	public void remove(String key) {
		this.data.remove(key);
	}
	
	public String get(String key) {
		return this.data.get(key);
	}
 
	public String getAndRemove(String key) 
	{
		return this.data.remove(key);
	}
	
	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> val) {
		this.data = val;
	}

	public Iterator<Entry<String, String>> iter()
	{ 
		return data.entrySet().iterator() ; 
	}

	public int size() {
		return data.size() ;
	}
	
	@Override
	public String toString() { 
		return RecordWriter.toJson( this );
	}
  
	private static final long serialVersionUID = -1729354052764864754L;

}
