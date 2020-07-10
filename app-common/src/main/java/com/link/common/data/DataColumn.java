package com.link.common.data ;

import org.apache.commons.lang.builder.ToStringBuilder ;

public class DataColumn implements java.io.Serializable
{ 
	private String	tableName	= null ;

	private String	colName		= null ;
	private String	colCnName	= null ;

	private int		colType		= 0 ;

	private int		rowLen		= 0 ;
	private int		rowScal		= 0 ;

	private boolean	isKeyCol	= false ;

	public String getTableName()
	{
		return tableName ;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName ;
	}

	public String getColName()
	{
		return colName ;
	}

	public void setColName(String colName)
	{
		this.colName = colName ;
	}

	public String getColCnName()
	{
		return colCnName ;
	}

	public void setColCnName(String colCnName)
	{
		this.colCnName = colCnName ;
	}

	public int getColType()
	{
		return colType ;
	}

	public void setColType(int colType)
	{
		this.colType = colType ;
	}

	public int getRowLen()
	{
		return rowLen ;
	}

	public void setRowLen(int rowLen)
	{
		this.rowLen = rowLen ;
	}

	public int getRowScal()
	{
		return rowScal ;
	}

	public void setRowScal(int rowScal)
	{
		this.rowScal = rowScal ;
	}

	public boolean isKeyCol()
	{
		return isKeyCol ;
	}

	public void setKeyCol(boolean isKeyCol)
	{
		this.isKeyCol = isKeyCol ;
	}

	
	public String toString()
	{
		return new ToStringBuilder("列信息[").append("keyCol", this.isKeyCol()).append("colName", this.colName).append("colType", this.colType).append("tableName", this.tableName).append("rowScal", this.rowScal).append("colCnName", this.colCnName)
				.append("rowLen", this.rowLen).append("]").toString() ;
	}
	
	
	private static final long	serialVersionUID	= -954466985357659556L ;
  
}
