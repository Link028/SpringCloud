package com.link.common.threadpool ;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FixedThreadPool  
{
	private static final Log LOG = LogFactory.getLog(FixedThreadPool.class);

	private int size;

	private ExecutorService _POOL = null;

	public FixedThreadPool(int _size)
	{
		size = _size; 
	}

	public void start() throws Exception {
		_POOL = Executors.newFixedThreadPool(size, new DefaultThreadFactory());
	}

	// 添加任务
	public void addTask(Runnable task) {
		this._POOL.execute(task);
	}

	public void stop() 
	{
		LOG.info("开始关闭线程池...");

		_POOL.shutdown();

		try
		{
			_POOL.awaitTermination( PoolConstans._DEF_QUEUE_TIMEOUT , TimeUnit.SECONDS);
		}
		catch (InterruptedException e)
		{
			LOG.warn("线程池关闭出错:" + e.getMessage(), e);
		}

		LOG.info("线程池关闭结束.");
	}

}
