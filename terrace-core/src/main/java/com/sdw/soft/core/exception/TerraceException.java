package com.sdw.soft.core.exception;
/**
 * @author syd
 * @Date 2013年12月1日
 * @version 1.0.0
 */
public class TerraceException extends BaseRuntimeException {

	private static final long serialVersionUID = -6444992588672613532L;

	public TerraceException(String msg) {
		super(msg);
	}
	
	public TerraceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	

}
