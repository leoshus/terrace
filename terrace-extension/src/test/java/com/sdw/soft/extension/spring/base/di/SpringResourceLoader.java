/**
 * @Date 2014年7月20日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.base.di;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.sdw.soft.extension.spring.base.di.vo.TestPrinter;

/**
 * 
 * @author syd
 */
public class SpringResourceLoader {

	private static final Logger logger = LoggerFactory.getLogger(SpringResourceLoader.class);
	
	public DefaultListableBeanFactory beanFactory;
	@Before
	public void setup(){
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource("/com/sdw/soft/extension/spring/base/di/applicationContext-testdi.xml");
		beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		int count = reader.loadBeanDefinitions(resource);
		logger.debug("Spring has loaded bean amount is :{}",count);
	}
	
	@Test
	public void testDependencyInject(){
		logger.debug("test Dependency Inject ---lookup-mehtod---- start...");
		TestPrinter testPrinter = beanFactory.getBean("singletonTestPrinter",TestPrinter.class);
		System.out.println("----------setter注入结果------------------");
		testPrinter.getPrinter().print();
		System.out.println("-----------lookup-method注入结果----------");
		testPrinter.getInstance().print();
		

		testPrinter = beanFactory.getBean("singletonTestPrinter",TestPrinter.class);
		System.out.println("----------setter注入结果------------------");
		testPrinter.getPrinter().print();
		System.out.println("-----------lookup-method注入结果----------");
		testPrinter.getInstance().print();

	}
}
