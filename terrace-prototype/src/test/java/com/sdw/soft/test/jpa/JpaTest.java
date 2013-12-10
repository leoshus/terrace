package com.sdw.soft.test.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdw.soft.auth.dao.UserDao;
import com.sdw.soft.auth.entity.User;

/**
 * @author syd
 * @Date 2013年12月3日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class JpaTest extends JpaAccessDataTest{
	@Autowired
	private UserDao userDao ;
	
	@Test
	public void testJpaSaveUser(){
		User user = new User();
		user.setAclCode("1");
		user.setUid("123");
		user.setId("123");
		user.setDisabled(Boolean.FALSE);
		userDao.save(user);
		
	}
}
