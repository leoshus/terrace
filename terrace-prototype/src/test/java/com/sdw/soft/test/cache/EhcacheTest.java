package com.sdw.soft.test.cache;

import junit.framework.Assert;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.sdw.soft.test.SpringTestCase;

/**
 * @author syd
 * @Date 2013年12月12日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class EhcacheTest extends SpringTestCase {
	
	private static final Logger logger = LoggerFactory.getLogger(EhcacheTest.class);
	
	@Autowired
	private EhCacheCacheManager cacheCacheManager;
	
	@Before
	public void setup(){
		if(cacheCacheManager != null){
			logger.info("CacheCacheManager Dependency Injection Success!");
		}else{
			logger.info("CacheCacheManager Dependency Injection Fail!");
		}
	}
	
	@Test
	public void testCache(){
		CacheManager cacheManager = cacheCacheManager.getCacheManager();
		Cache cache = cacheManager.getCache("SpringSecurityCache");
		Element element = new Element("test", "1234567890");
		cache.put(element);
		Element ele = cache.get("test");
		Assert.assertEquals(element.getObjectValue(), ele.getObjectValue());
		logger.info(ele.getObjectValue().toString());
	}
	
}
