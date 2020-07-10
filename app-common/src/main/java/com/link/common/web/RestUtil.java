package com.link.common.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.link.common.persist.util.ReflexObjectUtil;

public class RestUtil {

    public static <T>   RestData<T>  createBody( boolean isSuccess , String message  ) {
    	
    	RestData<T> resp = new RestData<T>();
    	
    	resp.setSuccess(isSuccess);
    	resp.setMessage( message ); 
    
        return resp ;
    }
	
    public static <T>   RestData<T>  createBody( boolean isSuccess , String	code , String message  ) {
    	
    	RestData<T> resp = new RestData<T>();
    	
    	resp.setSuccess(isSuccess);
    	resp.setCode( code );
    	resp.setMessage( message ); 
    
        return resp ;
    }
	
    public static <T>   RestData<T>  createBody( boolean isSuccess , String message ,  List<T> data ) {
    	
    	RestData<T> resp = new RestData<T>();
    	
    	resp.setSuccess(isSuccess);
    	resp.setMessage( message ); 
    	
    	resp.setRows( data);
    	 
        return resp ;
    }

    public static <T>   RestData<T>  createBody( boolean isSuccess , String message ,  List<T> data , long totalElements ,  int totalPages ) {
    	
    	RestData<T> resp = new RestData<T>();
    	
    	resp.setSuccess(isSuccess);
    	resp.setMessage( message ); 
    	
    	resp.setRows( data);
    	resp.setTotalElements(totalElements);
    	resp.setTotalPages(totalPages);
    	
        return resp ;
    }
    
    public static <T>   RestData<T>  createBody( boolean isSuccess , String	code , String message ,  List<T> data ) {
    	
    	RestData<T> resp = new RestData<T>();
    	
    	resp.setSuccess(isSuccess);
    	resp.setCode( code );
    	resp.setMessage( message ); 
    	
    	resp.setRows( data);
    	 
        return resp ;
    }

    public static <T>   RestData<T>  createBody( boolean isSuccess , String	code , String message ,  List<T> data , long totalElements ,  int totalPages) {
    	
    	RestData<T> resp = new RestData<T>();
    	
    	resp.setSuccess(isSuccess);
    	resp.setCode( code );
    	resp.setMessage( message ); 
    	
    	resp.setRows( data);
    	resp.setTotalElements(totalElements);
    	resp.setTotalPages(totalPages);
    	
        return resp ;
    }
    
    public static <T,TT> RestData<TT>  toRestBody( Page<T> page ,  TT t)
    {
    	//List<T> rows = page.getContent() ;
    	List<TT> data = new ArrayList<TT>();
    
//    	for (T tt : rows)
//		{
//    		Field[] fileds = tt.getClass().getFields();
//    		TT ttt = t.getClass().getName() ;    		
//		}
    	
    	RestData<TT> resp = new RestData<TT>();
    	
    	resp.setRows( data);
    	resp.setTotalElements( page.getTotalElements() );
    	resp.setTotalPages( page.getTotalPages() );
    	
        return resp ;
    }
    
    public static <T> RestData<Map<String,Object>>  toRestData( boolean isSuccess , String message , Page<T> page )
    {
    	RestData<Map<String,Object>> resp = toRestData(page) ;
    	
    	resp.setSuccess(isSuccess);
    	resp.setMessage(message);
    	
        return resp ;
    } 
    
    public static <T> RestData<Map<String,Object>>  toRestData( boolean isSuccess , String	code , String message , Page<T> page )
    {
    	RestData<Map<String,Object>> resp = toRestData(page) ;
    	
    	resp.setSuccess(isSuccess);
    	resp.setCode(code);
    	resp.setMessage(message);
    	
        return resp ;
    } 
    
    public static <T> RestData<Map<String,Object>>  toRestData( Page<T> page )
    {
    	List<T> rows = page.getContent() ;
    	
    	List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
    
    	for (T t : rows)
		{
    		Map<String, Object> row = ReflexObjectUtil.getKeyAndValue( t ) ;
    		data.add( row ) ;   		
		}
    	
    	RestData<Map<String,Object>> resp = new RestData<Map<String,Object>>();
    	
    	resp.setData( data);
    	resp.setTotalElements( page.getTotalElements() );
    	resp.setTotalPages( page.getTotalPages() );
    	
        return resp ;
    } 
    
    public static <T> RestData<Map<String,Object>>  toRestData( boolean isSuccess , String message ,  List<T> rows )
    {
    	RestData<Map<String,Object>> resp = toRestData( rows ) ;
    	
    	resp.setSuccess(isSuccess);
    	resp.setMessage(message);
    	
        return resp ;
    } 
    
    public static <T> RestData<Map<String,Object>>  toRestData( boolean isSuccess , String	code , String message ,  List<T> rows )
    {
    	RestData<Map<String,Object>> resp = toRestData( rows ) ;
    	
    	resp.setSuccess(isSuccess);
    	resp.setCode(code);
    	resp.setMessage(message);
    	
        return resp ;
    } 
    
    public static <T> RestData<Map<String,Object>>  toRestData( List<T> rows )
    {
    	List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
    
    	for (T t : rows)
		{
    		Map<String, Object> row = ReflexObjectUtil.getKeyAndValue( t ) ;
    		data.add( row ) ;   		
		}
    	
    	RestData<Map<String,Object>> resp = new RestData<Map<String,Object>>();
    	
    	resp.setData( data);
  
        return resp ;
    } 
    
    public static <T> RestData<Map<String,String>>  toRestData(  Map<String,String> rows )
    {
    	RestData<Map<String,String>> resp = new RestData<Map<String,String>>();
    	resp.setMap(rows);
        return resp ;
    }
    
    public static <T> RestData<Map<String,String>>  toRestData( boolean isSuccess , String message ,  Map<String,String> rows )
    {
    	RestData<Map<String,String>> resp = new RestData<Map<String,String>>();
    	resp.setMap(rows);
    	resp.setSuccess(isSuccess);
    	resp.setMessage(message);
    	
        return resp ;
    }
    
    public static <T> RestData<Map<String,String>>  toRestData( boolean isSuccess , String	code ,  String message ,  Map<String,String> rows )
    {
    	RestData<Map<String,String>> resp = new RestData<Map<String,String>>();
    	resp.setMap(rows);
    	resp.setCode(code);
    	resp.setSuccess(isSuccess);
    	resp.setMessage(message);
    	
        return resp ;
    } 
}
