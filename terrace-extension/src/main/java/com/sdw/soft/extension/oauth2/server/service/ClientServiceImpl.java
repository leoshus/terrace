/**
 * @Date 2014年4月2日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.oauth2.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sdw.soft.extension.oauth2.server.dao.ClientDao;
import com.sdw.soft.extension.oauth2.server.entity.Client;

/**
 * 
 * @author syd
 */
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Override
	public Client createClient(Client client) {
		return clientDao.save(client);
	}

	@Override
	public Client updateClient(Client client) {
		return clientDao.updateClient(client);
	}

	@Override
	public void deleteClient(String clientId) {
		clientDao.delete(clientId);
	}

	@Override
	public Client findOne(String clientId) {
		return clientDao.findOne(clientId);
	}

	@Override
	public List<Client> findAll() {
		return (List<Client>) clientDao.findAll();
	}

	@Override
	public Client findByClientId(String clientId) {
		return clientDao.findByClientId(clientId);
	}

	@Override
	public Client findByClientSecret(String clientSecret) {
		return clientDao.findByClientSecret(clientSecret);
	}

}
