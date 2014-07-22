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
public class TestPrinter {

	private Printer printer;

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	
	public Printer getPrinter() {
		return printer;
	}


	public Printer getInstance(){
		return null;
	}
}
