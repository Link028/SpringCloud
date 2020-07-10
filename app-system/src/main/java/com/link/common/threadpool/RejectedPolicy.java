package com.link.common.threadpool ;

import java.util.concurrent.RejectedExecutionException ;
import java.util.concurrent.RejectedExecutionHandler ;
import java.util.concurrent.ThreadPoolExecutor ;

import org.apache.commons.logging.Log ;
import org.apache.commons.logging.LogFactory ;

public class RejectedPolicy implements RejectedExecutionHandler
{
	private static final Log LOG = LogFactory.getLog(RejectedPolicy.class);
	
	public void rejectedExecution(Runnable r, ThreadPoolExecutor e)
	{
		LOG.error("线程池已满,Task:" + r.toString());
		
		throw new RejectedExecutionException("Task " + r.toString() + " rejected from " + e.toString());
	}
}
