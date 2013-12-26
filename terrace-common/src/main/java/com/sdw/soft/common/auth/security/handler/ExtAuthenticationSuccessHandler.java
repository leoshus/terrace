package com.sdw.soft.common.auth.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author syd
 * @Date 2013年12月26日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class ExtAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ExtAuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
