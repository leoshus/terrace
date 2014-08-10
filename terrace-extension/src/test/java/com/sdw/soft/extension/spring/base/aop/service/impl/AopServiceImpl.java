/**
 * @Date 2014年3月31日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.base.aop.service.impl;

import com.sdw.soft.extension.spring.base.aop.service.IAopService;

/**
 * 
 * @author syd
 */
public class AopServiceImpl implements IAopService {

	@Override
	public void sayHello(String param) {
		System.out.println(">>>>>>>>>>>AOP Hello World!<<<<<<<<<<<<<<<<---:"+param);
	}

	@Override
	public String saySomethingReturning() {
		System.out.println(">>>>>>>>>>saySomethingReturning<<<<<<<<<<<<<");
		return "say something";
	}

	@Override
	public void sayAfterThrowing() {
		System.out.println(">>>>>>>>>>>>>>AOP After Throwing<<<<<<<<<<<<<<<");
		throw new RuntimeException();
	}

	@Override
	public void sayAfterFinally() {
		System.out.println(">>>>>>>>>>>>>>>>AOP after finally<<<<<<<<<<");
	}

	@Override
	public void sayAround(String param) {
		System.out.println(">>>>>>>>>>>>>>>say around <<<<<<<<<<<<<<<:"+param);
	}

}
