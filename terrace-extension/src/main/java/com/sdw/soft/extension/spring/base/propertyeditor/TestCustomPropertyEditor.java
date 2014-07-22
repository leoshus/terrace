/**
 * @Date 2014年7月22日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.base.propertyeditor;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.sdw.soft.extension.spring.base.propertyeditor.vo.Person;

/**
 * 
 * @author syd
 */
public class TestCustomPropertyEditor {

	
	@Test
	public void testPropertyEditor(){
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource("/com/sdw/soft/extension/spring/base/propertyeditor/applicationContext-propertyEditor.xml");
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		int count = reader.loadBeanDefinitions(resource);
		Person person = beanFactory.getBean("person",Person.class);
		System.out.println(person.getComputer().getComputerName());
	}
}
