/**
 * @Date 2014年1月19日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.jackson.deserialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.sdw.soft.extension.jackson.vo.Item;
import com.sdw.soft.extension.jackson.vo.User;

/**
 * 
 * @author syd
 */
public class ItemDeserializer extends JsonDeserializer<Item> {

	@Override
	public Item deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		int id = (Integer)((IntNode)node.get("id")).numberValue();
		String itemName = node.get("itemName").asText();
		int userId = (Integer)((IntNode)node.get("owner")).numberValue();
		return new Item(id,itemName,new User(userId,null));
	}

}
