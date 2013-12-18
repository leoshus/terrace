package com.sdw.soft.test.jpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdw.soft.common.auth.dao.UserDao;
import com.sdw.soft.common.auth.entity.User;



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
		
		
	}
}
