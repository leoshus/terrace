package com.sdw.soft.common.sys.web.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sdw.soft.common.sys.entity.Calendar;
import com.sdw.soft.common.sys.service.CalendarService;
import com.sdw.soft.core.service.BaseService;
import com.sdw.soft.core.web.action.BaseController;

/**
 * @author syd
 * @Date 2013年12月31日
 * @version 1.0.0
 * Copyright (c) 2013
 */
public class CalendarController extends BaseController<Calendar, String> {

	private static final long serialVersionUID = -3317522815248845755L;

	@Autowired
	private CalendarService calendarService;
	
	@Override
	protected BaseService<Calendar, String> getEntityService() {
		return calendarService;
	}

	@Override
	protected void checkEntityAclPermission(Calendar entity) {
		
	}
	
	public HttpHeaders load() {
        List<Calendar> calendarList = calendarService.findAllWithNoPageNoSort();

         Lists.<Calendar, Map>transform(calendarList, new Function<Calendar, Map>() {
            @Override
            public Map apply(Calendar c) {
                Map<String, Object> m = Maps.newHashMap();

                Date startDate = new Date(c.getStartDate().getTime());
                Date endDate = DateUtils.addDays(startDate, c.getLength() - 1);
                boolean allDays = c.getStartTime() == null && c.getEndTime() == null;

                if(!allDays) {
                    startDate.setHours(c.getStartTime().getHours());
                    startDate.setMinutes(c.getStartTime().getMinutes());
                    startDate.setSeconds(c.getStartTime().getSeconds());
                    endDate.setHours(c.getEndTime().getHours());
                    endDate.setMinutes(c.getEndTime().getMinutes());
                    endDate.setSeconds(c.getEndTime().getSeconds());
                }

                m.put("id", c.getId());
                m.put("start", DateFormatUtils.format(startDate, "yyyy-MM-dd HH:mm:ss"));
                m.put("end", DateFormatUtils.format(endDate, "yyyy-MM-dd HH:mm:ss"));
                m.put("allDay", allDays);
                m.put("title", c.getTitle());
                m.put("details", c.getDetails());
                if(StringUtils.isNotEmpty(c.getBackgroundColor())) {
                    m.put("backgroundColor", c.getBackgroundColor());
                    m.put("borderColor", c.getBackgroundColor());
                }
                if(StringUtils.isNotEmpty(c.getTextColor())) {
                    m.put("textColor", c.getTextColor());
                }
                return m;
            }
        });
         setModel(calendarList);
         return buildDefaultHttpHeaders(); 
    }

}
