package com.sdw.soft.common.web.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.apache.struts2.rest.RestActionSupport;
import org.apache.struts2.views.freemarker.tags.SetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ModelDriven;
import com.sdw.soft.common.sys.service.MenuService;
import com.sdw.soft.common.sys.vo.NavMenuVO;

/**
 * @author syd
 * @Date 2013年12月18日
 * @version 1.0.0
 * Copyright (c) 2013
 */
@Namespace("/pub")
public class LayoutController extends RestActionSupport implements ModelDriven<Object> {

	private static final long serialVersionUID = -8804229024744027920L;

	private Object model;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MenuService menuService;
	
	@Override
	public Object getModel() {
		return model;
	}
	
	public void setModel(Object model){
		this.model = model;
	}
	/**
	 * 获取菜单数据
	 * @return
	 * @throws JsonProcessingException
	 */
	public HttpHeaders menu() throws JsonProcessingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		Set<GrantedAuthority> authories = new HashSet<GrantedAuthority>();
		authories.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
		List<NavMenuVO> menus = menuService.authUserMenu(authories, request.getContextPath());
		request.setAttribute("rootMenus", menus);
		request.setAttribute("menuJsonData", mapper.writeValueAsString(menus));
		setModel(menus);
		return new DefaultHttpHeaders().disableCaching();
	}
}
