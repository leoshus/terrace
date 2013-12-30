package com.sdw.soft.common.auth.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

/**
 * @author syd
 * @Date 2013年12月26日
 * @version 1.0.0
 * Copyright (c) 2013
 * 自定义登录成功后的回调地址 
 * http://cl315917525.iteye.com/blog/1768396
 */
public class ExtAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ExtAuthenticationSuccessHandler.class);

	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		logger.info("login success ....");
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		 if (savedRequest == null) {
	            super.onAuthenticationSuccess(request, response, authentication);

	            return;
	        }
	        String targetUrlParameter = getTargetUrlParameter();
	        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
	            requestCache.removeRequest(request, response);
	            super.onAuthenticationSuccess(request, response, authentication);

	            return;
	        }

	        clearAuthenticationAttributes(request);

	        // Use the DefaultSavedRequest URL
	        String targetUrl = savedRequest.getRedirectUrl();
	        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
	        String targeturl = this.determineTargetUrl(request, response);
	        logger.info("$$$$$$$$$$$$$$$$$$$$$$$${}",targeturl);
	        getRedirectStrategy().sendRedirect(request, response, targeturl);
	}
	
	public void setRequestCache(RequestCache requestCache) {  
        this.requestCache = requestCache;  
    } 
}
