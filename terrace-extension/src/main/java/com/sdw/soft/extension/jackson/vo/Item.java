package com.sdw.soft.extension.jackson.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sdw.soft.extension.jackson.deserialize.ItemDeserializer;
import com.sdw.soft.extension.jackson.serialize.ItemSerializer;

/**
 * @Date 2014年1月19日
 * @version 1.0.0
 * Copyright (c) 2014
 */
@JsonSerialize(using=ItemSerializer.class)
@JsonDeserialize(using=ItemDeserializer.class)
public class Item {
	public int id;
	private String itemName;
	private User owner;
	
	
	public Item() {
		super();
	}
	public Item(int id, String itemName, User owner) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
}
