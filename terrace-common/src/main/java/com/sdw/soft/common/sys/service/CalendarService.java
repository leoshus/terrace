package com.sdw.soft.common.sys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.sdw.soft.common.sys.dao.CalendarDao;
import com.sdw.soft.common.sys.entity.Calendar;
import com.sdw.soft.core.dao.BaseDao;
import com.sdw.soft.core.service.BaseService;

/**
 * @author syd
 * @Date 2013年12月31日
 * @version 1.0.0
 * Copyright (c) 2013
 */
@Service
@Transactional
public class CalendarService extends BaseService<Calendar, String> {

	private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);
	
	@Autowired
	private CalendarDao calendarDao ;
	
	@Override
	protected BaseDao<Calendar, String> getEntityDao() {
		return calendarDao;
	}
	
	public List<Calendar> findAllWithNoPageNoSort() {
		
		return Lists.newArrayList(calendarDao.findAllCached());
	}

}
