/**
 * @Date 2014年7月22日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.extension.spring.base.di.vo;
/**
 * 
 * @author syd
 */
public class Printer {
	private int count = 0;
	
	public void print(){
		count++;
		System.out.println("count is" + count);
	}
}
