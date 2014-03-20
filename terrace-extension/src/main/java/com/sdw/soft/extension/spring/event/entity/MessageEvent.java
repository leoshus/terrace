/**
 * @Date 2014年3月20日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.event.entity;

import org.springframework.context.ApplicationEvent;

/**
 * 
 * @author syd
 */
public class MessageEvent extends ApplicationEvent{

	private static final long serialVersionUID = -1798063105650625514L;
	
	private String message;

	public MessageEvent(Object source) {
		super(source);
	}
	
	public MessageEvent(Object source,String message){
		super(source);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
