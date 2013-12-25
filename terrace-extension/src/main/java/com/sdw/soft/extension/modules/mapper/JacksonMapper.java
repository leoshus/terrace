package com.sdw.soft.extension.modules.mapper;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * @author syd
 * @Date 2013年12月25日
 * @version 1.0.0
 * Copyright (c) 2013
 * 
 * 简单封装Jackson，实现JSON String<->Java Object的Mapper.
 * 
 */
public class JacksonMapper {
	private static final Logger logger = LoggerFactory.getLogger(JacksonMapper.class);
	
	private ObjectMapper mapper;
	public JacksonMapper(){
		this(null);
	}
	
	public JacksonMapper(Include include){
		mapper = new ObjectMapper();
		if(include != null){
			mapper.setSerializationInclusion(include);
		}
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
	}
	/**
	 * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
	 * @return
	 */
	public static JacksonMapper noEmptyMapper(){
		return new JacksonMapper(Include.NON_EMPTY);
	}
	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
	 * @return
	 */
	public static JacksonMapper noDefaultMapper(){
		return new JacksonMapper(Include.NON_DEFAULT);
	}
	/**
	 * Object可以是POJO，也可以是Collection或数组。
	 * 如果对象为Null, 返回"null".
	 * 如果集合为空集合, 返回"[]".
	 * @param object
	 * @return
	 */
	public String toJson(Object object){
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 反序列化POJO或简单Collection如List<String>.
	 * 
	 * 如果JSON字符串为Null或"null"字符串, 返回Null.
	 * 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, JavaType)
	 * @param json
	 * @param clazz
	 * @return
	 */
	public <T> T fromJson(String json,Class<T> clazz){
		if(StringUtils.isEmpty(json)){
			return null;
		}
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用createCollectionType()或contructMapType()构造类型, 然后调用本函数.
	 * @param json
	 * @param javaType
	 * @return
	 */
	public <T> T fromJson(String json,JavaType javaType){
		if(StringUtils.isEmpty(json)){
			return null;
		}
		try {
			return (T)mapper.readValue(json,javaType);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 构造Collection类型.
	 * @param collectionClass
	 * @param elementClass
	 * @return
	 */
	public JavaType constructCollectionType(Class<? extends Collection> collectionClass,Class<?> elementClass){
		return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
	}
	/**
	 * 构造Map类型.
	 * @param mapClass
	 * @param keyClass
	 * @param valueClass
	 * @return
	 */
	public JavaType constructMapType(Class<? extends Map> mapClass,Class<?> keyClass,Class<?> valueClass){
		return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
	}
	
	/**
	 * 当JSON里只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
	 * @param json
	 * @param object
	 */
	public void update(String json,Object object){
		try {
			mapper.readerForUpdating(object).readValue(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 转换成JSONP格式数据
	 * @param functionName
	 * @param object
	 * @return
	 */
	public String toJsonP(String functionName,Object object){
		return toJson(new JSONPObject(functionName,object));
	}
	/**
	 * 設定是否使用Enum的toString函數來讀寫Enum,
	 * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
	 * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
	 */
	public void enableEnumUseToString(){
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}
	/**
	 * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
	 * 默认会先查找jaxb的annotation，如果找不到再找jackson的。
	 */
//	public void enableJaxbAnnotation(){
//		JaxbAnnotationModule module = new JaxbAnnotationModule();
//		mapper.registerModule(module);
//	}
	
	public ObjectMapper getMapper(){
		return mapper;
	}
}
