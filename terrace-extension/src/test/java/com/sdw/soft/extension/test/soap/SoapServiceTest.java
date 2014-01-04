package com.sdw.soft.extension.test.soap;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.h2.constant.SysProperties;
import org.junit.Test;

import com.sdw.soft.extension.webservice.soap.IUserService;
import com.sdw.soft.extension.webservice.soap.response.UserDTO;

/**
 * @Date 2014年1月4日
 * @version 1.0.0
 * Copyright (c) 2014
 */
public class SoapServiceTest {
	
	private static final String WSDLURL = "http://localhost:8080/terrace-extension/tws/terrace/user?wsdl";
	@Test
	public void test01() throws Exception{
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		Client client = factory.createClient(WSDLURL);
		Object[] obj = client.invoke("getUserInfo", new Long(1));
		System.out.println(obj.toString());
	}
	
	@Test
	public void test02(){
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(WSDLURL);
		factory.setServiceClass(IUserService.class);
		IUserService userService = (IUserService)factory.create();
		UserDTO user = userService.getUserInfo(new Long(1));
		System.out.println(user.toString());
	}
}
