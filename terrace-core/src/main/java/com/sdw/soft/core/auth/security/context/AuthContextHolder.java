package com.sdw.soft.core.auth.security.context;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.sdw.soft.core.auth.security.vo.ExtUserDetails;

/**
 * @Date 2014年1月5日
 * @version 1.0.0
 * Copyright (c) 2014
 */
public class AuthContextHolder {
	
	private static final ThreadLocal<String> userPinContainer = new ThreadLocal<String>();
	public static final String DEFAULT_UNKNOWN_PIN = "N/A";
	
	public static void setAuthUserPin(String userPin) {
        userPinContainer.set(userPin);
    }
	/**
     * 获取用户唯一登录标识
     */
    public static String getAuthUserPin() {
        String pin = userPinContainer.get();
        if (StringUtils.isBlank(pin)) {
            ExtUserDetails authUserDetails = getAuthUserDetails();
            if (authUserDetails != null && authUserDetails.getUsername() != null) {
                pin = authUserDetails.getUsername();
            } else {
                pin = DEFAULT_UNKNOWN_PIN;
            }
        }
        return pin;
    }
    
    /**
     * 基于Spring Security获取用户认证信息
     */
    public static ExtUserDetails getAuthUserDetails() {
        ExtUserDetails userDetails = null;
        if (SecurityContextHolder.getContext() == null
                || SecurityContextHolder.getContext().getAuthentication() == null
                || SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
            return null;
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof ExtUserDetails) {
            userDetails = (ExtUserDetails) principal;
        }
        Assert.notNull(userDetails);
        return userDetails;
    }
}
