package com.sdw.soft.common.auth.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sdw.soft.common.auth.entity.RoleR2Privilege;
import com.sdw.soft.core.dao.BaseDao;

/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0
 * Copyright (c) 2013
 */
@Repository
public interface RoleR2PrivilegeDao extends BaseDao<RoleR2Privilege, String> {
	
	@Query("select r2 from RoleR2Privilege r2  inner join r2.privilege as p  inner join r2.role r where p.disabled=false and r.code != :excludeRoleCode")
    @QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    List<RoleR2Privilege> findEnabledExcludeRole(@Param("excludeRoleCode") String excludeRoleCode);

    List<RoleR2Privilege> findByPrivilege_Id(String privilegeId);
}
