/**
 * @Date 2014年4月1日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.oauth2.server.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sdw.soft.extension.oauth2.server.entity.Client;

/**
 * 
 * @author syd
 */
public class ClientDaoPlusImpl implements ClientDaoPlus {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Client updateClient(Client client) {
		jdbcTemplate.update("update oauth2_client set client_name=?, client_id=?, client_secret=? where id=?",
				new Object[]{client.getClientName(), client.getClientId(), client.getClientSecret(), client.getId()});
		return client;
	}

}
