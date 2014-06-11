/**
 * @Date 2014年6月6日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.test.resourcesload;

import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * 
 * @author syd
 */
public class ResourceLoad {

	@Test
	public void test01(){
		DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("classpath:com/sdw/soft/extension/test/resourcesload/applicationContext.xml");
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		int count = beanDefinitionReader.loadBeanDefinitions(resource);
		TestResourceLoader test = (TestResourceLoader)beanFactory.getBean("test");
		test.test();
		System.out.println(count);
		
	}
}
