package com.link.common.threadpool ;

import java.util.concurrent.RejectedExecutionHandler ;
import java.util.concurrent.ThreadPoolExecutor ;

import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;

public class ExitPolicy implements RejectedExecutionHandler
{
	private static final Log LOG = LogFactory.getLog(ExitPolicy.class);
	 
	public ExitPolicy()
	{ 
		 
	} 
	public void rejectedExecution(Runnable r, ThreadPoolExecutor e)
	{
		LOG.error("线程执行出错，系统退出:"+e);
		
		System.exit( -1 ) ;
	}

}
