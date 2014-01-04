package com.sdw.soft.extension.webservice.soap.impl;

import javax.jws.WebService;

import com.sdw.soft.extension.webservice.soap.IUserService;
import com.sdw.soft.extension.webservice.soap.response.UserDTO;

/**
 * @author syd
 * @Date 2014年1月4日
 * @version 1.0.0
 * Copyright (c) 2014
 * serviceName指明WSDL中<wsdl:service>与<wsdl:binding>元素的名称, endpointInterface属性指向Interface类全称.
 */
@WebService(serviceName="UserService",endpointInterface="com.sdw.soft.extension.webservice.soap.IUserService")
public class UserServiceImpl implements IUserService {

	@Override
	public UserDTO getUserInfo(Long id) {
		//省略了dao层的逻辑
		UserDTO user = new UserDTO();
		user.setId(new Long(1));
		user.setLoginName("Tom");
		user.setName(" 汤姆");
		user.setEmail("terrace@terrace.com");
		
		return user;
	}

}
