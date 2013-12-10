package com.sdw.soft.auth.dao;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sdw.soft.auth.entity.UserR2Role;
import com.sdw.soft.core.dao.BaseDao;

/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0 Copyright (c) 2013
 */
@Repository
public interface UserR2RoleDao extends BaseDao<UserR2Role, String> {
	@Query("select r2 from Role r, UserR2Role r2 where r=r2.role and r2.user.id=:userId and r.disabled=false ")
	@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
	List<UserR2Role> findEnabledRolesForUser(@Param("userId") String userId);

	List<UserR2Role> findByRole_Id(String roleId);

	@Query("select r2 from User u, UserR2Role r2, Role r where r=r2.role and u=r2.user and r2.user.id=:userId order by r.aclType desc, r.code")
	List<UserR2Role> findByUser_Id(@Param("userId") String userId);
}
