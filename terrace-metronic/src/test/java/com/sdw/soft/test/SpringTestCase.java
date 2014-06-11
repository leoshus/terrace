package com.sdw.soft.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * @author syd
 * @Date 2013年12月3日
 * @version 1.0.0
 * Copyright (c) 2013
 * 
 *  Spring的支持数据库访问, 事务控制和依赖注入的JUnit4 集成测试基类.
 * 相比Spring原基类名字更短并保存了dataSource变量.
 *   
 * 子类需要定义applicationContext文件的位置, 如:
 * @ContextConfiguration(locations = { "/applicationContext.xml" })
 */
@ActiveProfiles("mysql")
@ContextConfiguration(locations = {"classpath*:/context/spring-*.xml"})
public class SpringTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected DataSource dataSource;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}
}
