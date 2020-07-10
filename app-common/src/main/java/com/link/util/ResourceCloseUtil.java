package com.link.util;

import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;

public class ResourceCloseUtil
{
	protected static final Log _LOG = LogFactory.getLog(ResourceCloseUtil.class);

	// ///////////////////////////////////////////////////////////////////////////
	 
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
				_LOG.error("关闭资源出错:"+e.getMessage(),e);
			}

			closeable = null;
		}
	}
 
	public static void close(AutoCloseable closeable)
	{
		if ( closeable != null )
		{
			try
			{
				closeable.close();
			}
			catch (Exception e)
			{
				_LOG.error("关闭资源出错:"+e.getMessage(),e);
			}

			closeable = null;
		}
	}
	
	
	
}
