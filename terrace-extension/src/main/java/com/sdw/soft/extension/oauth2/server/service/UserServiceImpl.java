/**
 * @Date 2014年4月2日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.oauth2.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sdw.soft.extension.oauth2.server.dao.UserDao;
import com.sdw.soft.extension.oauth2.server.entity.User;

/**
 * 
 * @author syd
 */
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User createUser(User user) {
		return userDao.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userDao.update(user);
	}

	@Override
	public void deleteUser(String userId) {
		userDao.delete(userId);
	}

	@Override
	public void changePassword(String userId, String newPassword) {
		
	}

	@Override
	public User findOne(String userId) {
		return userDao.findOne(userId);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
