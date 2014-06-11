/**
 * @Date 2014年4月1日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.oauth2.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sdw.soft.extension.oauth2.server.entity.User;

/**
 * 
 * @author syd
 */
public class UserDaoPlusImpl implements UserDaoPlus {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User update(User user) {
		jdbcTemplate.update("update oauth2_user set username=?, password=?, salt=? where id=?", new Object[]{user.getUsername(), user.getPassword(), user.getSalt(), user.getId()});
		return user;
	}

}
