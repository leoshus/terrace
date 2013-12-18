package com.sdw.soft.common.auth.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.sdw.soft.common.auth.entity.Role;
import com.sdw.soft.core.dao.BaseDao;

/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0
 * Copyright (c) 2013
 */
@Repository
public interface RoleDao extends BaseDao<Role, String> {
	
	@Query("from Role")
    @QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    List<Role> findAllCached();
    
    @QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    Role findByCode(String code);
}
