package com.sdw.soft.core.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloController extends ActionSupport {

	private static final long serialVersionUID = -285169001981328551L;
	
	public String execute(){
		return "index";
	}

}
