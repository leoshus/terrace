package com.sdw.soft.core.dao.jpa;

import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

/**
 * @author syd
 * @Date 2013年12月3日
 * @version 1.0.0
 * Copyright (c) 2013
 */
/**
 * 扩展JPA Hibernate持久对象Post处理逻辑
 * 主要是为了获取MutablePersistenceUnitInfo对象从而可以获取Hibernate Entity元数据
 * @author hp
 *
 */
public class ExtPersistenceUnitPostProcessor implements PersistenceUnitPostProcessor {

	private MutablePersistenceUnitInfo mutablePersistenceUnitInfo;
	
	public MutablePersistenceUnitInfo getMutablePersistenceUnitInfo() {
		return mutablePersistenceUnitInfo;
	}

	@Override
	public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
		this.mutablePersistenceUnitInfo = pui;
	}

}
