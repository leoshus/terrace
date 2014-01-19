package com.sdw.soft.extension.jackson.vo;
/**
 * @Date 2014年1月19日
 * @version 1.0.0
 * Copyright (c) 2014
 */
public class User {
	private int id;
	private String username;
	
	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
