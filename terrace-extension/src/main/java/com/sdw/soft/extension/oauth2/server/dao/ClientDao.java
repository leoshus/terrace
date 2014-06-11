/**
 * @Date 2014年4月1日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.oauth2.server.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sdw.soft.extension.oauth2.server.entity.Client;

/**
 * 
 * @author syd
 */
public interface ClientDao extends PagingAndSortingRepository<Client, String> ,JpaSpecificationExecutor<Client>,ClientDaoPlus{
	
	public Client findByClientId(String clientId);
	
	public Client findByClientSecret(String clientSecret);	
}
