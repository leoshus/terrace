/**
 * @Date 2014年3月31日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.base.aop.support;

import org.aspectj.lang.ProceedingJoinPoint;
import org.h2.constant.SysProperties;

/**
 * 
 * @author syd
 */
public class HelloWorldAspect {
	
	public void beforeAdvice(String param){
		System.out.println(">>>>>>>>>>>>>>before advice<<<<<<<<<<<<<<");
	}
	
	public void afterFinallyAdvice(){
		System.out.println(">>>>>>>>>>>>>after finally advice<<<<<<<<<<<<");
	}
	
	public void afterReturningAdvice(Object obj){
		System.out.println(">>>>>>>>>>>>>>>afterReturningAdvice<<<<<<<<<<<<<<<:"+obj);
	}
	
	
	public void afterThrowingAdvice(Exception exception){
		System.out.println(">>>>>>>>>>>>>>>>after throwing advice<<<<<<<<<<<<<<<<<<<<<<:" + exception);
	}
	
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>aop around advice before<<<<<<<<<<<<<<<<<<<<<<");
		Object obj = pjp.proceed(new Object[]{"replace"});
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>aop around advice after<<<<<<<<<<<<<<<<<<<<");
		return obj;
	}
	
}
