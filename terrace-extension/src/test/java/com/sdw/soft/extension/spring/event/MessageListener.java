/**
 * @Date 2014年3月20日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

import com.sdw.soft.extension.spring.event.entity.MessageEvent;


/**
 * 
 * @author syd
 */
public class MessageListener implements ApplicationListener {

	private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof MessageEvent){
			logger.debug("This is the MessageEvent... ...");
			MessageEvent message = (MessageEvent)event;
			logger.debug("本次发布的消息为:{}",message.getMessage());
		}else if(event instanceof ContextClosedEvent){
			logger.debug("{},事件已发生!",event.getClass().getSimpleName());
		}else if(event instanceof ContextRefreshedEvent){
			logger.debug("{},事件已发生!",event.getClass().getSimpleName());
		}else if(event instanceof ContextStartedEvent){
			logger.debug("{},事件已发生!",event.getClass().getSimpleName());
		}else if(event instanceof ContextStoppedEvent){
			logger.debug("{},事件已发生!",event.getClass().getSimpleName());
		}else{
			logger.debug("Other event is carryed out ... ...");
		}
	}

}
