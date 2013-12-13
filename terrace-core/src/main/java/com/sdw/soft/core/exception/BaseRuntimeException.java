package com.sdw.soft.core.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author syd
 * @Date 2013年12月1日
 * @version 1.0.0
 */
public class BaseRuntimeException extends NestedRuntimeException {

	private static final long serialVersionUID = -3552807487210843625L;
	
	public BaseRuntimeException(String msg) {
		super(msg);
	}
	public BaseRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
