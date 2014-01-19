/**
 * @Date 2014年1月19日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.jackson.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sdw.soft.extension.jackson.vo.Item;

/**
 * 自定义 json serializer
 * @author syd
 */
public class ItemSerializer extends JsonSerializer<Item> {

	@Override
	public void serialize(Item object, JsonGenerator jsonGen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		jsonGen.writeStartObject();
		jsonGen.writeNumberField("id", object.id);
		jsonGen.writeStringField("itemName", object.getItemName());
		jsonGen.writeNumberField("owner", object.getOwner().getId());
		jsonGen.writeEndObject();
	}

}
