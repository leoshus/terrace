/**
 * @Date 2014年1月22日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author syd
 */
public class PersonAccount implements Callable {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonAccount.class);
	
	Integer totalMoney;
	@Override
	public Object call() throws Exception {
		Thread.sleep(5000);
		totalMoney = new Integer(new Random().nextInt(100000));
		logger.info("you account has" + totalMoney + "$");
		return totalMoney;
	}

}
