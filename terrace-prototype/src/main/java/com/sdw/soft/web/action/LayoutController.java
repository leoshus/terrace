package com.sdw.soft.web.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author syd
 * @Date 2013年11月30日
 * @version 1.0.0
 */
public class LayoutController extends ActionSupport {

	private static final long serialVersionUID = -8432399457840777389L;
	
	public String welcome(){
		return "welcome";
	}
	
	public String menu(){
		return "menu";
	}
}
