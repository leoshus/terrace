/**
 * @Date 2014年4月1日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.oauth2.server.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sdw.soft.extension.oauth2.server.entity.User;

/**
 * 
 * @author syd
 */
public interface UserDao extends PagingAndSortingRepository<User, String>,JpaSpecificationExecutor<User>,UserDaoPlus {
	
	User findByUsername(String username);
	
}
