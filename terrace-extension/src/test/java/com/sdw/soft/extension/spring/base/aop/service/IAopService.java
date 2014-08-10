/**
 * @Date 2014年3月31日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.base.aop.service;
/**
 * 
 * @author syd
 */
public interface IAopService {
	
	public void sayHello(String param);
	
	public String saySomethingReturning();
	
	public void sayAfterThrowing();
	
	public void sayAfterFinally();
	
	public void sayAround(String param);
}
