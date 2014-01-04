package com.sdw.soft.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * @author syd
 * @Date 2014年1月4日
 * @version 1.0.0
 * Copyright (c) 2014
 */
/**
 * 自定义Struts2的核心过滤器 防止struts过滤掉CXF的访问路径
 */
public class ExtStrutsPrepareAndExecuteFilter extends StrutsPrepareAndExecuteFilter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		if(request.getRequestURI().contains("tws")){
			chain.doFilter(req, res);
		}else{
			super.doFilter(req, res, chain);
		}
	}
}
