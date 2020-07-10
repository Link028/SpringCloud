package com.link.util;

import java.text.DateFormat ;
import java.text.SimpleDateFormat ;
import java.util.UUID ;
import java.util.concurrent.locks.ReentrantLock ;

public class UUIDUtil {
	 
	public static  String getUUID()
	{ 
		String s = UUID.randomUUID().toString(); 
		 
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
	}
	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replaceAll("-","" ));
	}
	
	public static final DateFormat _DATE_FORMAT_YYYYMMDDHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	
	private static final int _MAX = 1000000 ;
	private static final int _MIN = 0 ;
	private static final int _LEN = 6 ;
	private static final String PRIFIX = "0" ;
	
	private static int _SEQ = 0 ;
	
	private static final ReentrantLock _LOCK = new ReentrantLock();
	
	public static  String getEventId()
	{  
		int seq = 0 ;
		
		_LOCK.lock();
		
		if( _SEQ == _MAX )
		{
			_SEQ = _MIN ;
		}
		
		seq = ++ _SEQ ;
		
		_LOCK.unlock() ;
		
		StringBuilder sb = new StringBuilder() ;
		 
		sb.append( _DATE_FORMAT_YYYYMMDDHHMMSSSSS.format( System.currentTimeMillis()) ) ;
		
		String str = String.valueOf( seq ) ;
		
		for (int i = str.length() ; i <= _LEN ; i++)
		{
			sb.append( PRIFIX ) ;
		}
		 	
		sb.append( str ) ;
		 
		return sb.toString() ; 
	}
	

}
