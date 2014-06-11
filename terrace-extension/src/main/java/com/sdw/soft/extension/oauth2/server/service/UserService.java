/**
 * @Date 2014年4月1日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.oauth2.server.service;

import java.util.List;

import com.sdw.soft.extension.oauth2.server.entity.User;

/**
 * 
 * @author syd
 */
public interface UserService {
	 /**
     * 创建用户
     * @param user
     */
    public User createUser(User user);

    public User updateUser(User user);

    public void deleteUser(String userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(String userId, String newPassword);

    User findOne(String userId);

    List<User> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);
}
