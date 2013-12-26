package com.sdw.soft.common.web.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.rest.RestActionSupport;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author syd
 * @Date 2013年12月26日
 * @version 1.0.0
 * Copyright (c) 2013
 */
@Namespace("/pub")
public class LandingController extends RestActionSupport implements ModelDriven<Object>{

	private static final long serialVersionUID = -7523871077709103397L;

	@Override
	public Object getModel() {
		return null;
	}

}
