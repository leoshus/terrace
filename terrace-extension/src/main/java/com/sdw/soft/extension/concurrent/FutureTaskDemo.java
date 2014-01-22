/**
 * @Date 2014年1月22日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author syd
 */
public class FutureTaskDemo {
	
	private static final Logger logger = LoggerFactory.getLogger(FutureTaskDemo.class);
	
	@Test
	public void test01(){
		Callable account = new PersonAccount();
		FutureTask futureTask = new FutureTask(account);
		
		Thread threadFT = new Thread(futureTask);
		logger.info("the thread futureTask start run at" + System.nanoTime());
		threadFT.start();
		logger.info("main thread start to carry out other task");
		
		int totalMoney = new Random().nextInt(100000);
		logger.info("you has money " + totalMoney +"in other account.");
		logger.info("waiting the statities end");
		while(!futureTask.isDone()){
			try {
				Thread.sleep(5000);
				logger.info("the statities not end ...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("the thread futureTask end at"+System.nanoTime());
		Integer privateAccountMoney = null;
		try {
			privateAccountMoney = (Integer)futureTask.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		logger.info("you have money "+totalMoney+"+"+privateAccountMoney+"="+(totalMoney+privateAccountMoney));
	}
	
	@Test
	public void test02(){
		Executor executor = Executors.newFixedThreadPool(100);
		FutureTask<String> command = new FutureTask<String>(new Callable<String>(){

			@Override
			public String call() throws Exception {
				//do something asychronize thing
				return null;
			}
		});
		executor.execute(command);
		// do some other thing
		
		try {
			String result = command.get(500, TimeUnit.MILLISECONDS);//设置超时5秒 取得结果
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
