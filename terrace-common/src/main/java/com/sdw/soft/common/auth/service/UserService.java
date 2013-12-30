package com.sdw.soft.common.auth.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.sdw.soft.common.auth.dao.PrivilegeDao;
import com.sdw.soft.common.auth.dao.RoleDao;
import com.sdw.soft.common.auth.dao.UserDao;
import com.sdw.soft.common.auth.dao.UserR2RoleDao;
import com.sdw.soft.common.auth.entity.Privilege;
import com.sdw.soft.common.auth.entity.Role;
import com.sdw.soft.common.auth.entity.User;
import com.sdw.soft.common.auth.entity.UserR2Role;
import com.sdw.soft.common.auth.security.service.AclService;
import com.sdw.soft.core.auth.security.vo.ExtUserDetails;
import com.sdw.soft.core.dao.BaseDao;
import com.sdw.soft.core.service.BaseService;
import com.sdw.soft.core.service.R2OperationEnum;

/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0
 * Copyright (c) 2013
 */
@Service
@Transactional
public class UserService extends BaseService<User, String> {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PrivilegeDao privilegeDao;
	
	@Autowired
	private UserR2RoleDao userR2RoleDao;
	
	@Autowired(required=false)//FIXME 织入PasswordEncoder 待加入spring security完善
	private PasswordEncoder passwordEncoder;
	
	@Autowired(required = false)
	private AclService aclService;
	
	@Override
	protected BaseDao<User, String> getEntityDao() {
		return userDao;
	}

	@Override
	protected void preInsert(User entity) {
		super.preInsert(entity);
		if(aclService != null){
			entity.setAclType(aclService.aclCodeToType(entity.getAclCode()));
		}
		entity.setSignupTime(new Date());
	}

	@Override
	protected void preUpdate(User entity) {
		if(aclService != null){
			entity.setAclType(aclService.aclCodeToType(entity.getAclCode()));
		}
		super.preUpdate(entity);
	}
	
	public User findByAclCodeAndSigninid(String aclCode, String signid) {
        if (StringUtils.isBlank(aclCode)) {
            List<User> users = userDao.findBySigninid(signid);
            if (CollectionUtils.isEmpty(users)) {
                return null;
            }
            Assert.isTrue(users.size() == 1);
            return users.get(0);
        }
        return userDao.findByAclCodeAndSigninid(aclCode, signid);
    }
	
	public User findByUid(String uid) {
        return userDao.findByUid(uid);
    }

    public long findCount() {
        return userDao.count();
    }

    /**
     * 用户注册
     * 
     * @param rawPassword
     *            原始密码
     * @param user
     *            用户数据对象
     * @return
     */
    public User save(User user, String rawPassword) {
        if (user.isNew()) {
            user.setUid(RandomStringUtils.randomNumeric(10));
        }
        if (StringUtils.isNotBlank(rawPassword)) {
            user.setPassword(encodeUserPasswd(user, rawPassword));
        }
        return this.save(user);
    }

    public String encodeUserPasswd(User user, String rawPassword) {
        return passwordEncoder.encodePassword(rawPassword, user.getUid());
    }

    /**
     * 初始化系统用户设置
     * @param user
     * @param rawPassword
     * @return
     */
    public User initSetupUser(User user, String rawPassword) {
        long count = findCount();
        Assert.isTrue(count == 0);
        user.setInitSetupUser(true);
        save(user, rawPassword);
        Role role = roleDao.findByCode(Role.ROLE_ADMIN_CODE);
        if (role == null) {
            role = new Role();
            role.setCode(Role.ROLE_ADMIN_CODE);
            role.setTitle("预置系统管理员");
            role.setDescription("预置角色,不可删除");
            roleDao.save(role);
        }
        UserR2Role r2 = new UserR2Role();
        r2.setUser(user);
        r2.setRole(role);
        userR2RoleDao.save(r2);
        return user;
    }

    @Transactional(readOnly = true)
    public List<UserR2Role> findRelatedUserR2RolesForUser(User user) {
    	return userR2RoleDao.findByUser(user);
    }

    public void updateRelatedRoleR2s(String id, Collection<String> roleIds, R2OperationEnum op) {
        updateRelatedR2s(id, roleIds, "userR2Roles", "role", op);
    }

    @Transactional(readOnly = true)
    public List<Privilege> findRelatedPrivilegesForUser(User user) {
    	 return privilegeDao.findPrivilegesForUser(user);
    }

    
    public UserDetails loadUserDetails(String username){
    	logger.debug("load user details for: {}" + username);
    	User user = findByProperty("signinid", username);
    	if(user == null){
    		throw new UsernameNotFoundException("username for "+ username +"not found!");
    	}
    	boolean disabled = user.getDisabled() == null ? false : user.getDisabled();
        boolean accountNonLocked = user.getAccountNonLocked() == null ? true : user.getAccountNonLocked();
        Date now = new Date();
        boolean credentialsNonExpired = user.getCredentialsExpireTime() == null ? true : user
                .getCredentialsExpireTime().after(now);
        boolean accountNonExpired = user.getAccountExpireTime() == null ? true : user.getAccountExpireTime().after(now);

        if (disabled) {
            throw new DisabledException("User '" + username + "' disabled");
        }
        if (!credentialsNonExpired) {
            throw new CredentialsExpiredException("User '" + username + "' credentials expired");
        }
        if (!accountNonLocked) {
            throw new LockedException("User '" + username + "' account locked");
        }
        if (!accountNonExpired) {
            throw new AccountExpiredException("User '" + username + "' account expired");
        }

        Set<GrantedAuthority> authSets = new HashSet<GrantedAuthority>();
        Iterable <UserR2Role> userR2Roles = userR2RoleDao.findEnabledRolesForUser(user);
        for(UserR2Role userR2Role : userR2Roles){
        	String roleCode = userR2Role.getRole().getCode();
        	authSets.add(new SimpleGrantedAuthority(roleCode));
        }
        authSets.add(new SimpleGrantedAuthority(Role.ROLE_ANONYMOUSLY_CODE));
        
        if(logger.isDebugEnabled()){
        	logger.debug("User have roles for: {}",username);
        	for(GrantedAuthority grantedAuthority : authSets){
        		logger.debug("---"+grantedAuthority.getAuthority());
        	}
        }
        
        ExtUserDetails userDetails = new ExtUserDetails(user.getPassword(),username,authSets,accountNonExpired,accountNonLocked,credentialsNonExpired,!disabled);
        userDetails.setUid(user.getUid());
        userDetails.setAclCode(user.getAclCode());
        userDetails.setAclType(user.getAclType());
        userDetails.setEmail(user.getEmail());
        
        if(aclService != null){
        	userDetails.setAclCodePrefixs(aclService.getStatAclCodePrefixs(user.getAclCode()));
        }
        // 处理用户拥有的权限代码集合
        Set<String> privilegeCodeSet = new HashSet<String>();
        List<Privilege> privileges = privilegeDao.findPrivilegesForUser(user);
        for (Privilege privilege : privileges) {
            privilegeCodeSet.add(privilege.getCode().trim());
        }
        userDetails.setPrivilegeCodes(privilegeCodeSet);
    	return userDetails;
    }
    /*@Async
    public void userLogonLog(UserLogonLog userLogonLog) {
        User user = userDao.findByUid(userLogonLog.getUserid());
        if (user.getLogonTimes() == null) {
            user.setLogonTimes(1L);
        } else {
            user.setLogonTimes(user.getLogonTimes() + 1L);
        }
        userLogonLog.setLogonTimes(user.getLogonTimes());
        user.setLastLogonIP(userLogonLog.getRemoteAddr());
        user.setLastLogonHost(userLogonLog.getRemoteHost());
        user.setLastLogonTime(userLogonLog.getLogonTime());
        userDao.save(user);
        userLogonLogDao.save(userLogonLog);
    }*/
}
