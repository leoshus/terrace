/**
 * @Date 2014年6月5日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import org.junit.Test;

/**
 * 
 * @author syd
 */
public class ConcurrentTest {

	@Test
	public void test01(){
		Executor threadPool = null;
		/*
		 *创建一个固定大小为20的线程池 
		 */
		threadPool = Executors.newFixedThreadPool(20);
		/*
		 * 创建大小不受限制的可缓存的线程池
		 */
		threadPool = Executors.newCachedThreadPool();
		/*
		 * 创建一个单工作线程的线程池
		 */
		threadPool = Executors.newSingleThreadExecutor();
		/*
		 * 创建一个固定长度的线程池而且以延迟或定时的方式来执行任务的线程池
		 */
		threadPool = Executors.newScheduledThreadPool(20);
		/*
		 * 每个创建线程池的方法都有ThreadFactory的重载
		 */
		threadPool = Executors.newFixedThreadPool(20, new ThreadFactory(){
			//控制线程产生的细节操作
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setDaemon(true);//设置为守护线程
				thread.setPriority(Thread.MAX_PRIORITY);//设置最大优先级
				return thread;
			}
		});
		/*
		 * 提交一个任务
		 */
		threadPool.execute(new Runnable(){

			@Override
			public void run() {
				// doSomething...
			}
			
		});
		/*我们一般使用ExecutorService，方便我们更好地操作线程池*/
		ExecutorService executorService = (ExecutorService)threadPool;
		/*
		 * 提交有返回值的任务
		 */
		Future<String> result = executorService.submit(new Callable<String>(){
			@Override
			public String call() throws Exception {
				// to do something...
				return null;
			}
		});
		/*
		 * 温柔地关闭线程池
		 */
		executorService.shutdown();
		/*
		 * 粗暴的关闭线程池
		 */
		executorService.shutdownNow();
		
	}
}
