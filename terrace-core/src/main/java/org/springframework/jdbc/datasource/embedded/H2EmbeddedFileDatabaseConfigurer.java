package org.springframework.jdbc.datasource.embedded;

import java.sql.Driver;

import org.springframework.util.ClassUtils;

/**
 * @author syd
 * @Date 2013年12月17日
 * @version 1.0.0
 * Copyright (c) 2013
 * 
 * Spring JDBC默认只支持内存模式的H2嵌入数据库 @see H2EmbeddedDatabaseConfigurer
 * 扩展实现基于File文件模式的H2嵌入式数据库，开发过程就不再需要每次初始化数据库
 * 在实际配置过程中注意以databaseName指定H2数据绝对路径文件， 配置示例:
 * <bean id="dataSourceH2" class="org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean">
    		<property name="databaseName" value="D:\\h2\\terrace"/>
    		<property name="databaseConfigurer">
    			<bean class="org.springframework.jdbc.datasource.embedded.H2EmbeddedFileDatabaseConfigurer" factory-method="getInstance"/>
    		</property>
    	</bean>
 */
public class H2EmbeddedFileDatabaseConfigurer extends AbstractEmbeddedDatabaseConfigurer{

	private static H2EmbeddedFileDatabaseConfigurer INSTANCE;
	
	private final Class<? extends Driver> driverClass;
	/**
	 * Get the singleton {@link H2EmbeddedDatabaseConfigurer} instance.
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static synchronized H2EmbeddedFileDatabaseConfigurer getInstance() throws ClassNotFoundException{
		if(INSTANCE == null){
			INSTANCE = new H2EmbeddedFileDatabaseConfigurer((Class<? extends Driver>)ClassUtils.forName("org.h2.Driver",H2EmbeddedFileDatabaseConfigurer.class.getClassLoader()));
		}
		return INSTANCE;
	}
	@Override
	public void configureConnectionProperties(ConnectionProperties properties,
			String databaseName) {
		properties.setDriverClass(this.driverClass);
		//参数DB_CLOSE_DELAY是要求最后一个正在连接的连接断开后，不要关闭DB，因为下一个case可能还会有新连接进来。
		properties.setUrl(String.format("jdbc:h2:file:%s;DB_CLOSE_DELAY=-1",databaseName));
		properties.setUsername("sa");
		properties.setPassword("sa");
	}
	private H2EmbeddedFileDatabaseConfigurer(Class<? extends Driver> driverClass) {
		this.driverClass = driverClass;
	}

	
}
