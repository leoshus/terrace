package com.sdw.soft.test.jpa;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sdw.soft.common.auth.dao.UserDao;



/**
 * @author syd
 * @Date 2013年12月3日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class JpaTest extends JpaAccessDataTest{
	@Autowired
	private UserDao userDao ;
	
	@Test
	public void testJpaSaveUser() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yycj?useUnicode=true&characterEncoding=UTF-8", "root", "root");
		PreparedStatement stat = conn.prepareStatement("select * from action_column_config");
		ResultSet rs = stat.executeQuery();
		while(rs.next()){
			System.out.println("ok>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
	}
}

