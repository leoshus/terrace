/**
 * @Date 2014年1月21日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import com.sdw.soft.core.utils.web.Servlets;

/**
 * 为Response设置客户端缓存控制Header的Filter.
 * <pre>
 * 	<filter>
 * 		<filter-name>cacheControlHeaderFilter</filter-name>
 * 		<filter-class>org.springside.modules.web.CacheControlHeaderFilter</filter-class>
 * 		<init-param>
 * 			<param-name>expiresSeconds</param-name>
 * 			<param-value>31536000</param-value>
 * 		</init-param>
 * 	</filter>
 * 	<filter-mapping>
 * 		<filter-name>cacheControlHeaderFilter</filter-name>
 * 		<url-pattern>/images/*</url-pattern>
 * 	</filter-mapping>
 * </pre>
 */
public class CacheControlHeaderFilter implements Filter {
	
	private static final String PARAM_EXPIRES_SECONDS = "expiresSeconds";
	private long expiresSeconds;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		Servlets.setExpiresHeader((HttpServletResponse)resp, expiresSeconds);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String expiresSecondsParam = config.getInitParameter(PARAM_EXPIRES_SECONDS);
		if(expiresSecondsParam != null){
			expiresSeconds = Long.valueOf(expiresSecondsParam);
		}else{
			expiresSeconds = Servlets.ONE_YEAR_SECONDS;
		}
	}

}
