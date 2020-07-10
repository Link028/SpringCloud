package com.link.common.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QueuedThreadPool
{
	private static final Log LOG = LogFactory.getLog(QueuedThreadPool.class);

	private int	_queueSize;
	private int	_minThread;
	private int	_maxThread;
	private int	_aliveTime;

	private RejectedExecutionHandler _handler = null; 
	private TimeUnit _TIME_UNIT = PoolConstans._DEF_TIME_UNIT;

	protected BlockingQueue<Runnable>	_QUEUE	= null;
	protected ThreadPoolExecutor		_POOL	= null;

	public QueuedThreadPool(int queueSize, int minThread, int maxThread) 
	{
		this(queueSize, minThread, maxThread, PoolConstans._DEF_QUEUE_KEEP_ALIVE_TIME, PoolConstans._DEFAULT_HANDLER);
	}

	public QueuedThreadPool(int queueSize, int minThread, int maxThread, int aliveTime) 
	{
		this(queueSize, minThread, maxThread, aliveTime, PoolConstans._DEFAULT_HANDLER);
	}

	public QueuedThreadPool(int queueSize, int minThread, int maxThread, int aliveTime, RejectedExecutionHandler handler) 
	{
		this._queueSize = queueSize;
		this._minThread = minThread;
		this._maxThread = maxThread;
		this._aliveTime = aliveTime;
		this._handler = handler;
	}

	public void start()  {
		LOG.info("初始化线程队列");

		_QUEUE = new ArrayBlockingQueue<Runnable>(this._queueSize);

		//
		_POOL = new ThreadPoolExecutor(this._minThread, this._maxThread, this._aliveTime, this._TIME_UNIT, _QUEUE, new DefaultThreadFactory(), _handler);

		LOG.info("线程队列初始化结束.");
	}

	// 添加任务
	public void addTask(QueueTask task) {
		this._POOL.execute(task);
	}

	public void stop() {
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

	
	public static void main(String[] args) throws InterruptedException {
		

		QueuedThreadPool pool = new   QueuedThreadPool(10, 10, 10) ;
		pool.start();
	   
		for (int i = 0; i < 100; i++)
		{
			System.out.println("put--------------------"+i);
			pool.addTask( 
					new QueueTask() {
						public void run()
						{
							try
							{
								Thread.sleep(5000);
								System.out.println("run--------------------");
							}
							catch (InterruptedException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					);
		}
	   
	   
	   
	   
	}
	
	
	
	
	
}
