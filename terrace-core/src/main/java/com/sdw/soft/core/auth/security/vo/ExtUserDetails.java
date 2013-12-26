package com.sdw.soft.core.auth.security.vo;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author syd
 * @Date 2013年12月11日
 * @version 1.0.0
 * Copyright (c) 2013
 * 
 * 扩展spring security 认证过程中使用的用户vo类
 */
public class ExtUserDetails implements UserDetails {

	private static final long serialVersionUID = 5129680188651654956L;

	private String uid;//全局用户标识
	
	private String password;//登录密码
	
	private String username;//登录用户
	
	private String email;
	
	private Set<GrantedAuthority> authorities;
	
	private boolean accountNonExpired = true;
	
	private boolean accountNonLocked = true;
	
	private boolean credentialsNonExpired = true;
	
	private boolean enabled ;
	
	private Collection<String> privilegeCodes;
	
	private String aclCode;//数据访问控制代码
	
	private Integer aclType;//数据访问控制类型(数字大权限包含数字小)
	
	private Map<String,Object> attributes;//扩展属性容器，如CAS Oauth认证返回用户信息 
	
	private Collection<String> aclCodePrefixs;//基于一个单一ACL Code返回其可以访问的ACL Code前缀集合 如用户ACL Code为120000，根据业务规则其访问前缀集合可转化12, AA12,BB12等
	
	
	public ExtUserDetails(String password, String username,
			Set<GrantedAuthority> authorities, boolean accountNonExpired,
			boolean accountNonLocked, boolean credentialsNonExpired,
			boolean enabled) {
		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}
		this.password = password;
		this.username = username;
		this.authorities = authorities;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<String> getPrivilegeCodes() {
		return privilegeCodes;
	}

	public void setPrivilegeCodes(Collection<String> privilegeCodes) {
		this.privilegeCodes = privilegeCodes;
	}

	public String getAclCode() {
		return aclCode;
	}

	public void setAclCode(String aclCode) {
		this.aclCode = aclCode;
	}

	public Integer getAclType() {
		return aclType;
	}

	public void setAclType(Integer aclType) {
		this.aclType = aclType;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Collection<String> getAclCodePrefixs() {
		return aclCodePrefixs;
	}

	public void setAclCodePrefixs(Collection<String> aclCodePrefixs) {
		this.aclCodePrefixs = aclCodePrefixs;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
}
