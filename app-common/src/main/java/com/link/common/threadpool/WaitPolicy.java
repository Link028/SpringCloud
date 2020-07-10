package com.link.common.threadpool ;

import java.util.concurrent.RejectedExecutionException ;
import java.util.concurrent.RejectedExecutionHandler ;
import java.util.concurrent.ThreadPoolExecutor ;
import java.util.concurrent.TimeUnit ;

public class WaitPolicy implements RejectedExecutionHandler
{

	private final long		_time ;
	private final TimeUnit	_timeUnit ;

	 
	public WaitPolicy()
	{ 
		this(Long.MAX_VALUE, TimeUnit.SECONDS) ;
	}

 
	public WaitPolicy(long time, TimeUnit timeUnit)
	{ 
		_time = (time < 0 ? Long.MAX_VALUE : time) ;
		_timeUnit = timeUnit ;
	}

	public void rejectedExecution(Runnable r, ThreadPoolExecutor e)
	{
		try
		{
			if (e.isShutdown() || !e.getQueue().offer(r, _time, _timeUnit))
			{
				throw new RejectedExecutionException() ;
			}
		}
		catch (InterruptedException ie)
		{
			Thread.currentThread().interrupt() ;
			throw new RejectedExecutionException(ie) ;
		}
	}

}
