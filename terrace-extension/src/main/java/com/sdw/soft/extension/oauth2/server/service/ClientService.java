/**
 * @Date 2014年4月1日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.oauth2.server.service;

import java.util.List;

import com.sdw.soft.extension.oauth2.server.entity.Client;

/**
 * 
 * @author syd
 */
public interface ClientService {
	 public Client createClient(Client client);
	    public Client updateClient(Client client);
	    public void deleteClient(String clientId);

	    Client findOne(String clientId);

	    List<Client> findAll();

	    Client findByClientId(String clientId);
	    Client findByClientSecret(String clientSecret);
}
