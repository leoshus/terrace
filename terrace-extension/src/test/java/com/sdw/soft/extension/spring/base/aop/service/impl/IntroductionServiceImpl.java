/**
 * @Date 2014年3月31日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.base.aop.service.impl;

import com.sdw.soft.extension.spring.base.aop.service.IIntroductionService;

/**
 * 
 * @author syd
 */
public class IntroductionServiceImpl implements IIntroductionService{

	@Override
	public void induct() {
		System.out.println(">>>>>>>>>>>>>>>>Introduction<<<<<<<<<<<<<<<");
	}

}
