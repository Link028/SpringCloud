package com.link.common.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.TimeUnit;

public class PoolConstans
{
	public static final int	_DEF_QUEUE_SIZE				= 100000;
	public static final int	_DEF_QUEUE_KEEP_ALIVE_TIME	= 36000; 
	public static final int	_DEF_QUEUE_TIMEOUT				= 120;
	
	public static final TimeUnit _DEF_TIME_UNIT = TimeUnit.SECONDS;

	public static final RejectedExecutionHandler _DEFAULT_HANDLER = new WaitPolicy();
}
