package com.sdw.soft.extension.test.jackson;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.sdw.soft.extension.jackson.deserialize.ItemDeserializer;
import com.sdw.soft.extension.jackson.serialize.ItemSerializer;
import com.sdw.soft.extension.jackson.vo.Item;
import com.sdw.soft.extension.jackson.vo.User;

/**
 * @Date 2014年1月19日
 * @version 1.0.0
 * Copyright (c) 2014
 */
public class TestJackson {
	private static  final Logger logger = LoggerFactory.getLogger(TestJackson.class);
	/**
	 * json serialize non annotation
	 * @throws JsonProcessingException 
	 */
	@Test
	public void test01() throws JsonProcessingException{
		Item item = new Item(1,"theitem",new User(1,"theuser"));
		String jsonResult = new ObjectMapper().writeValueAsString(item);
		logger.info("test01---jsonResult:"+jsonResult);
	}
	/**
	 * 自定义json serializer
	 * @throws JsonProcessingException 
	 */
	@Test
	public void test02() throws JsonProcessingException{
		Item item = new Item(1,"theitem",new User(1,"theuser"));
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(Item.class,new ItemSerializer());
		mapper.registerModule(module);
		String result = mapper.writeValueAsString(item);
		logger.info("test02---result:" + result);
	}
	
	/**
	 * test02 方法简化版本
	 * 在Item类中加入注解 @JsonSerializer(using=ItemSerializer.class)
	 * @throws JsonProcessingException 
	 */
	@Test
	public void test03() throws JsonProcessingException{
		Item item = new Item(1,"theitem",new User(1,"theuser"));
		String result = new ObjectMapper().writeValueAsString(item);
		logger.info("test03---result:" + result);
	}
	/**
	 * deserializer
	 * 
	 */
	@Test
	public void test04() throws Exception{
		Item item = new Item(1,"theitem",new User(1,"theuser"));
		String result = new ObjectMapper().writeValueAsString(item);
		logger.info("test04---result:" + result);
		
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Item.class, new ItemDeserializer());
		mapper.registerModule(module);
		Item it = mapper.readValue(result, Item.class);
		logger.info("test04---deserializer result :" + it.getId()+","+it.getItemName()+","+it.getOwner().getId());
	}
	
	/**
	 * test04 简化版本
	 * deserializer annotation 版本 需在Item中添加@JsonDeserializer(ItemDeserializer.class)
	 */
	@Test
	public void test05() throws Exception{
		Item item = new Item(1,"theitem",new User(2,"theuser"));
		String result = new ObjectMapper().writeValueAsString(item);
		logger.info("test05---result:" + result);
		
		Item it = new ObjectMapper().readValue(result, Item.class);
		logger.info("test05---deserializer result :" + it.getId()+","+it.getItemName()+","+it.getOwner().getId());
	}
}
