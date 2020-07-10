package com.link.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceUtil
{
	public static final String SEPARATOR = "/";
	
	/////////////////////////////////////////////////////////////////////////////
	
	public static String getClassPathFileSubDir(String resFile)
	{
		String subPath =  resFile.replaceAll("\\\\", SEPARATOR ) ;
		
		if(  subPath.indexOf(  SEPARATOR  ) <= 0 )
		{
			return "";
		}
		
		String confPath = resFile.substring(0, resFile.lastIndexOf( SEPARATOR )) ;
		
//		if (!confPath.endsWith( SEPARATOR ))
//		{
//			confPath = confPath + SEPARATOR ;
//		}
		
		if ( confPath.length() > 0 && ! confPath.endsWith( SEPARATOR ))
		{
			confPath = confPath + SEPARATOR ;
		}
		
		return confPath ;
	}
	
	public static String getClassPathRootDir(String classPathRes)
	{
		String rootClassPath = null ;
		try
		{
			String path = ClassUtils.getClassLoader(ResourceUtil.class).getResource(classPathRes).toURI().getPath(); 
			rootClassPath = new File(path).getAbsolutePath(); 
		}
		catch (Exception e1)
		{
			URL url = ClassUtils.getClassLoader(ResourceUtil.class).getResource("application.properties") ;

			try
			{
				rootClassPath = new File(url.getFile(), ".").getCanonicalPath() ;
			}
			catch (IOException e)
			{
				throw new RuntimeException("获取配置文件路径出错:" + e.getMessage(), e) ;
			}
		}

		rootClassPath = rootClassPath.replaceAll("\\\\", SEPARATOR ) ;

		rootClassPath = rootClassPath.substring(0, rootClassPath.lastIndexOf( "/" )) ;
		
		if (!rootClassPath.endsWith( SEPARATOR ))
		{
			rootClassPath = rootClassPath + SEPARATOR ;
		}
		
		return rootClassPath ;
	}
	
	public static String getClassPathRootDir()
	{
		String rootClassPath = null ;
		try
		{
			String path = ClassUtils.getClassLoader(ResourceUtil.class).getResource("").toURI().getPath(); 
			rootClassPath = new File(path).getAbsolutePath(); 
		}
		catch (Exception e1)
		{
			URL url = ClassUtils.getClassLoader(ResourceUtil.class).getResource("application.properties") ;

			try
			{
				rootClassPath = new File(url.getFile(), ".").getCanonicalPath() ;
			}
			catch (IOException e)
			{
				throw new RuntimeException("获取配置文件路径出错:" + e.getMessage(), e) ;
			}
		}
		 
		rootClassPath = rootClassPath.replaceAll("\\\\", SEPARATOR ) ;

		if (!rootClassPath.endsWith( SEPARATOR ))
		{
			rootClassPath = rootClassPath + SEPARATOR ;
		}
		
		return rootClassPath ;
		
//		String parentPath = rootClassPath.substring(0, rootClassPath.lastIndexOf( SEPARATOR )) ;
//
//		if (!parentPath.endsWith( SEPARATOR ))
//		{
//			parentPath = parentPath + SEPARATOR ;
//		}
//		return parentPath ;
	}
	
	public static String getClassPathRootParentDir(String parentFolder)
	{ 
		String rootClassPath = getClassPathRootDir() ;
	 
		File file = new File( rootClassPath ) ;

		String parentDir = file.getParent() ;

		parentDir = parentDir.replaceAll("\\\\", SEPARATOR ) ;
		  
		if ( ! parentDir.endsWith( SEPARATOR ) )
		{
			parentDir = parentDir + SEPARATOR ;
		}

		String parentPath = parentDir + parentFolder ;

		if (!parentPath.endsWith( SEPARATOR ))
		{
			parentPath = parentPath + SEPARATOR ; 
		}
 
		return parentPath ;
	}
	
 
	public static String getClassPathFileParentDir(String resFile)
	{
		// URL url = classLoader.getResource("/");
		URL url = ClassUtils.getClassLoader(ResourceUtil.class).getResource(resFile) ;

		//System.out.println( "url============" + url ) ;

		String fullPath = null ;
		try
		{
			fullPath = new File(url.getFile(), ".").getCanonicalPath() ;
		}
		catch (IOException e)
		{
			throw new RuntimeException("获取配置文件路径出错:" + e.getMessage(), e) ;
		}

		fullPath = fullPath.replaceAll("\\\\", SEPARATOR) ;

		String parentPath = fullPath.substring(0, fullPath.lastIndexOf(SEPARATOR)) ;

		if (!parentPath.endsWith(SEPARATOR))
		{
			parentPath = parentPath + SEPARATOR ;
		}

		return parentPath ;
	}
 
	public static String chechPath(String confPath)
	{
		confPath = confPath.replaceAll("\\\\", "/") ;
		if (!confPath.endsWith("/"))
		{
			confPath = confPath + "/" ;
		}
		
		return confPath ;
	}
	
	/**
	 * 判断文件是否存在
	 */
	public static boolean isFileExists(String fileName)
	{
		File file = new File(fileName) ;

		boolean exits = file.exists() ;

		file = null ;

		return exits ;
	}
	
	public static URL getClassPathResource(String resFile)
	{
		return ClassUtils.getClassLoader(ResourceUtil.class).getResource(resFile) ;
	}
	public static InputStream getClassPathResourceStrean(String resFile)
	{
		return ClassUtils.getClassLoader(ResourceUtil.class).getResourceAsStream(resFile) ;
	}

	public static void main(String[] args)
	{
		//String			_CONF_FILE				= "jms/kafka/kafka.xml" ;

		//String _CONF_FILE = "msg/kafka.xml" ;

		//String path = getClassPathFileParentDir(_CONF_FILE) ;

		//System.out.println(path) ;

		//    	String path = "E:\\Workspace\\MsgCenter\\bin\\jms\\kafka\\kafka.xml";
		//    	
		//    	path = path.replaceAll("\\\\", "/") ;
		//    	
		//    	_CONF_FILE				=  _CONF_FILE.replaceAll("\\\\", "/") ;
		//    	
		//    	
		//    	System.out.println(_CONF_FILE) ;
		//    	System.out.println(path) ;
		//    	
		//    	
		//    	int n = path.lastIndexOf( _CONF_FILE ) ;
		//    	
		//    	String subDir = path.substring( 0 , n ) ;
		//    	
		//    	System.out.println( subDir ) ;
 
		String str = getClassPathFileSubDir("cache/hbase/hbase-site.xml");

		System.out.println( str ) ; 
	}

	/////////////////////////////////////////////////////////////////////////////
	 
	public static void close(java.io.Closeable closeable)
	{
		if ( closeable != null )
		{
			try
			{
				closeable.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			closeable = null;
		}
	}
 
	public static void close(java.lang.AutoCloseable closeable)
	{
		if ( closeable != null )
		{
			try
			{
				closeable.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			closeable = null;
		}
	}
	
	
}
