/**
 * @Date 2014年3月31日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.base.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sdw.soft.extension.spring.base.aop.service.IAopService;
import com.sdw.soft.extension.spring.base.aop.service.IIntroductionService;

/**
 * 
 * @author syd
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/context/spring-aop.xml"})
public class AopTest {

	private static final Logger logger = LoggerFactory.getLogger(AopTest.class);
	
	@Autowired
	private IAopService aopService;

	@Autowired
	private IIntroductionService introductionService;
	
	@Test
	public void test01(){
		logger.debug("start...");
		//aopService.sayHello("TOM");
		//aopService.saySomethingReturning();
		//aopService.sayAfterThrowing();
		//aopService.sayAround("hello");
		introductionService.induct();
	}
}
