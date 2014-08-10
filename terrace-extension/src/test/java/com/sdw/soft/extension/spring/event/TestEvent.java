/**
 * @Date 2014年3月20日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.event;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author syd
 */
public class TestEvent {
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("/context/test-spring-context.xml");
		MessagePublisher messagePublisher = (MessagePublisher) context.getBean("messagePublisher");
		messagePublisher.sendMessage();		
		context.refresh();
		context.start();
		context.stop();
		context.close();
	}
}
