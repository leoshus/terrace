package com.sdw.soft.extension.webservice.soap;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.sdw.soft.extension.webservice.soap.response.UserDTO;

/**
 * @author syd
 * @Date 2014年1月4日
 * @version 1.0.0
 * Copyright (c) 2014
 * 
 * JAX-WS2.0的WebService接口定义类.
 * 
 * 使用JAX-WS2.0 annotation设置WSDL中的定义.
 * 使用WSResult及其子类类包裹返回结果.
 * 使用DTO传输对象隔绝系统内部领域对象的修改对外系统的影响.
 * 
 * name 指明wsdl中<wsdl:portType>元素的名称
 */
@WebService(name="IUserService")
public interface IUserService {
	public UserDTO getUserInfo(@WebParam(name="id") Long id);
}
