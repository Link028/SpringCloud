package com.link.common.persist.util;

import java.math.BigDecimal ;
import java.sql.PreparedStatement ;
import java.sql.ResultSet ;
import java.sql.ResultSetMetaData;
import java.sql.SQLException ;
import java.sql.Types ;
import java.text.DecimalFormat ;

import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;

import com.link.util.StringUtils;

public class DatabaseHelper
{  
    protected static final Log _LOG = LogFactory.getLog(DatabaseHelper.class);
    
	/** 空值列 */
	public static final String	_DATA_NULL					= "" ;	
    
    //-------------------------------
    //设置SQL值
  	public static String setValue(  int sqlType , String val ) 
  	{ 
  		if( val == null )
  		{
  			return null ;
  		}
  		
  		if( Types.CHAR == sqlType  || Types.VARCHAR == sqlType )
  		{ 
  			 return "'"+val+"'";
  		}
  		else 
  		{  
  			return val ;
  		} 
  	}
  
  	//设置查询值
  	public static void setValue( int index , int sqlType , PreparedStatement pstat ,String val ) throws SQLException
  	{
  		if( Types.CHAR == sqlType  || Types.VARCHAR == sqlType )
  		{ 
  			if( val != null )
  			{
  				 pstat.setString( index , val ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.VARCHAR) ;
  			}
  		}
  		else if( Types.TINYINT == sqlType  || Types.SMALLINT == sqlType )
  		{  
  			if( ! StringUtils.isEmpty( val ) )
  			{
  				 pstat.setShort( index , Short.parseShort( val ) ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.SMALLINT) ;
  			}
  		}
  		else if( Types.INTEGER == sqlType   )
  		{
  			if( ! StringUtils.isEmpty( val ) )
  			{
  				 pstat.setInt( index , Integer.parseInt( val ) ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.INTEGER) ;
  			}
  		}
  		else if( Types.BIGINT == sqlType   )
  		{
  			if( ! StringUtils.isEmpty( val ) )
  			{
  				 pstat.setLong( index , Long.parseLong( val ) ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.BIGINT) ;
  			}
  		}
  		else if( Types.FLOAT == sqlType   )
  		{ 
  			if( ! StringUtils.isEmpty( val ) )
  			{
  				 pstat.setFloat( index ,  Float.parseFloat(val ) ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.FLOAT) ;
  			}
  		}
  		else if( Types.DOUBLE == sqlType   )
  		{
  			if( ! StringUtils.isEmpty( val ) )
  			{
  				 pstat.setDouble( index , Double.parseDouble(val ) ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.DOUBLE) ;
  			}
  		}
  		else if( Types.DECIMAL == sqlType   )
  		{ 
  			if( ! StringUtils.isEmpty( val ) )
  			{
  				 pstat.setBigDecimal( index ,  new BigDecimal( val ) ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.DECIMAL) ;
  			}
  		}
  		else if( Types.DATE == sqlType   )
  		{ 
  			if( ! StringUtils.isEmpty( val ) )
  			{
  				 pstat.setDate( index , new java.sql.Date( Long.parseLong( val ) ) ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.DATE) ;
  			}
  		}
  		else if( Types.TIME == sqlType   )
  		{
  			if( ! StringUtils.isEmpty( val ) )
  			{
  				 pstat.setTime( index , new java.sql.Time( Long.parseLong( val ) ) ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.TIME) ;
  			}
  		}
  		else if( Types.TIMESTAMP == sqlType   )
  		{
  			if( ! StringUtils.isEmpty( val ) )
  			{
  				 pstat.setTimestamp( index ,  new java.sql.Timestamp( Long.parseLong( val ) ) ) ;
  			}
  			else
  			{
  				pstat.setNull( index , Types.TIMESTAMP) ;
  			} 
  		}
  		else
  		{
  			pstat.setObject( index , val ) ;
  		}
  	}
    
   //-------------------------------
   //获取查询值
  	public static String getValue(ResultSetMetaData meta, int index, ResultSet resultSet) throws SQLException 
  	{
		int colType = meta.getColumnType(index);
		String colName = meta.getColumnName(index);
		return getValue(colType, colName, resultSet);
	}
  	 
	//获取查询值
	public static String getValue( int sqlType , String colName ,ResultSet resultSet ) throws SQLException
	{ 
		if( Types.CHAR == sqlType  || Types.VARCHAR == sqlType )
		{
			String val = resultSet.getString( colName )  ; 
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : val ;
		}
		else if( Types.TINYINT == sqlType  || Types.SMALLINT == sqlType )
		{
			Short val = resultSet.getShort( colName )  ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : Short.toString( val ) ;
		}
		else if( Types.INTEGER == sqlType   )
		{
			Integer val = resultSet.getInt( colName )  ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : Integer.toString( val ) ;
		}
		else if( Types.BIGINT == sqlType   )
		{
			Long val = resultSet.getLong( colName )  ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : Long.toString( val ) ;
		}
		else if( Types.FLOAT == sqlType   )
		{
			Float val = resultSet.getFloat( colName )  ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : DecimalFormat.getInstance().format( val).replaceAll(",", "") ;
		}
		else if( Types.DOUBLE == sqlType   )
		{
			Double val = resultSet.getDouble( colName )  ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : DecimalFormat.getInstance().format(val).replaceAll(",", "") ;
		}
		else if( Types.DECIMAL == sqlType   )
		{
			BigDecimal val = resultSet.getBigDecimal( colName )  ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : DecimalFormat.getInstance().format(val).replaceAll(",", "") ;
		}
		else if( Types.DATE == sqlType   )
		{
			String val = resultSet.getString( colName )  ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : val.toString() ;
		}
		else if( Types.TIME == sqlType   )
		{
			String val = resultSet.getString( colName )  ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : val.toString() ;
		}
		else if( Types.TIMESTAMP == sqlType   )
		{
			String val = resultSet.getString( colName )  ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : val.toString() ;
		}
		else
		{
			Object val = resultSet.getObject( colName ) ;
			if( resultSet.wasNull() ) return _DATA_NULL ; 
			return  val  == null ? _DATA_NULL : val.toString() ;
		}
	}
	   
}
