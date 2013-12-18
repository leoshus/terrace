package com.sdw.soft.common.sys.dao;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.sdw.soft.common.sys.entity.Menu;
import com.sdw.soft.core.dao.BaseDao;

/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0
 * Copyright (c) 2013
 */
@Repository
public interface MenuDao extends BaseDao<Menu, String> {
	@Query("from Menu")
    @QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    public Iterable<Menu> findAllCached();
}
