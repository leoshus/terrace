package com.sdw.soft.common.auth.security.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.sdw.soft.common.auth.service.PrivilegeService;

/**
 * @author syd
 * @Date 2013年12月26日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class ExtFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static final Logger logger = LoggerFactory.getLogger(ExtFilterInvocationSecurityMetadataSource.class);
	
	private PrivilegeService privilegeService;
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		//此处借助Spring的Cache机制避免每次查询数据库
		Map<String,Collection<ConfigAttribute>> configAttributeMap = privilegeService.loadResourceDefine();
		String url = null;
		if(object instanceof String){
			url = (String)object;
		}else{
			url = ((FilterInvocation)object).getRequestUrl();
		}
		Assert.notNull(url);
		Iterator<String> iter = configAttributeMap.keySet().iterator();
		while(iter.hasNext()){
			String resURL = iter.next();
			if(antPathMatcher.match(resURL, url)){
				return configAttributeMap.get(resURL);
			}
		}
		return Lists.newArrayList();
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		logger.debug("Just return null for getAllConfigAttributes...");
        /**
         * 如果需要在启动时加载权限数据验证，可取消以下注释初始化逻辑       
        Map<String, Collection<ConfigAttribute>> resourceMap= privilegeService.loadResourceDefine();
        Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
        for (Collection<ConfigAttribute> ca : resourceMap.values()) {
            configAttributes.addAll(ca);
        }
        return configAttributes;
        */
        return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
