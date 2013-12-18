package com.sdw.soft.common.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.sdw.soft.core.annotation.MetaData;
import com.sdw.soft.core.entity.BaseEntity;

/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0
 * Copyright (c) 2013
 */

@Entity
@Table(name = "T_AUTH_ROLE_R2_PRIVILEGE", uniqueConstraints = @UniqueConstraint(columnNames = { "PRIVILEGE_ID",
        "ROLE_ID" }))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@MetaData(title = "角色与权限关联")
public class RoleR2Privilege extends BaseEntity<String> {

	private static final long serialVersionUID = -5771282551572306998L;

	/** 关联权限对象 */
    private Privilege privilege;

    /** 关联角色对象 */
    private Role role;
    
    private String id;

    @Id
    @Column(length = 40)
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        if (id == null || StringUtils.isBlank(id)) {
            this.id = null;
        } else {
            this.id = id;
        }
    }

    @ManyToOne
    @JoinColumn(name = "PRIVILEGE_ID", nullable = false)
    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    @Transient
    public String getDisplayLabel() {
        return null;
    }

}
