/**
 * @Date 2014年7月22日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.base.propertyeditor;

import java.beans.PropertyEditorSupport;

import com.sdw.soft.extension.spring.base.propertyeditor.vo.Computer;

/**
 * 
 * @author syd
 */
public class ExtPropertyEditor extends PropertyEditorSupport {
	
	private String format;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Computer computer = new Computer(org.apache.commons.lang3.StringUtils.upperCase(text));
		this.setValue(computer);
	}
	
}
