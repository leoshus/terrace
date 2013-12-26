package com.sdw.soft.common.auth.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sdw.soft.common.auth.service.UserService;

/**
 * @author syd
 * @Date 2013年12月11日
 * @version 1.0.0
 * Copyright (c) 2013
 * 
 * 扩展spring security 认证时authenticationManager使用的认证业务逻辑UserDetailsService
 */
public class ExtUserDetailsService implements UserDetailsService {

	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userService.loadUserDetails(username);
	}

}
