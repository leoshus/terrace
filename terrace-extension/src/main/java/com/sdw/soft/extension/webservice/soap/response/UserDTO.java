package com.sdw.soft.extension.webservice.soap.response;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * @author syd
 * @Date 2014年1月4日
 * @version 1.0.0
 * Copyright (c) 2014
 */
@XmlRootElement
@XmlType(name="User")
public class UserDTO {
	private Long id;
	private String loginName;
	private String name;
	private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return this.id + "---" + this.loginName + "---" + this.name + "---" + this.email;
	}
}
