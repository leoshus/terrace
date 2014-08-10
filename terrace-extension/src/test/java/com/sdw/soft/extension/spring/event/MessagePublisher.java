/**
 * @Date 2014年3月20日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.sdw.soft.extension.spring.event.entity.MessageEvent;

/**
 * 
 * @author syd
 */
public class MessagePublisher implements ApplicationEventPublisherAware {
	
	private static final Logger logger = LoggerFactory.getLogger(MessagePublisher.class);
	
	private String message ;

	private ApplicationEventPublisher messageEventPublisher;
	
	public void setMessage(String message) {
		this.message = message;
	}

	
	public String getMessage() {
		return message;
	}


	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		logger.debug("applicationEventPublisher:{}",applicationEventPublisher.toString());
		this.messageEventPublisher = applicationEventPublisher;
	}
	
	public void sendMessage(){
		MessageEvent messageEvent = new MessageEvent(messageEventPublisher,message);
		messageEventPublisher.publishEvent(messageEvent);
	}

}
