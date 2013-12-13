package org.apache.struts2.rest;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.apache.struts2.StrutsConstants;
import org.apache.struts2.rest.handler.ContentTypeHandler;

import com.fasterxml.jackson.databind.ObjectReader;
import com.opensymphony.xwork2.inject.Inject;
import com.sdw.soft.core.utils.json.JacksonMapperFactory;

/**
 * @author syd
 * @Date 2013年12月13日
 * @version 1.0.0
 * Copyright (c) 2013
 * 
 * Struts2默认只提供Jackson1版本支持,增强补充提供Jackson2版本支持
 */
public class Jackson2ContentTypeHandler implements ContentTypeHandler {

	private static final String DEFAULT_CONTENT_TYPE = "application/json";
	
	private String defaultEncoding = "UTF-8";
	
	@Override
	public void toObject(Reader in, Object target) throws IOException {
		ObjectReader or = JacksonMapperFactory.getObjectMapper().readerForUpdating(target);
		or.readValue(in);//FIXME new TypeReference<clazz>
	}

	@Override
	public String fromObject(Object obj, String resultCode, Writer stream)
			throws IOException {
		JacksonMapperFactory.getObjectMapper().writeValue(stream, obj);
		return null;
	}

	@Override
	public String getContentType() {
		return DEFAULT_CONTENT_TYPE + ";charset=" + this.defaultEncoding;
	}

	@Override
	public String getExtension() {
		return "json";
	}

	@Inject(StrutsConstants.STRUTS_I18N_ENCODING)
	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}
}
