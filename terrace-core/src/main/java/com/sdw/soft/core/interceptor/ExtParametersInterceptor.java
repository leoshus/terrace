package com.sdw.soft.core.interceptor;

import com.opensymphony.xwork2.interceptor.ParametersInterceptor;

/**
 * @Date 2014年1月9日
 * @version 1.0.0
 * Copyright (c) 2014
 */
public class ExtParametersInterceptor extends ParametersInterceptor {

	private static final long serialVersionUID = 3740636600210870215L;
	/**
	 * 过滤掉_=12132323参数
	 */
	@Override
	protected boolean isAccepted(String paramName) {
		boolean matches = super.isAccepted(paramName);
		if(paramName.equals("_")){
			matches = false;
		}
		
		return matches;
	}
}
