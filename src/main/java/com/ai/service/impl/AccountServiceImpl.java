package com.ai.service.impl;

import com.ai.dao.*;
import com.ai.domain.bo.AuthRole;
import com.ai.domain.bo.AuthUser;
import com.ai.domain.bo.AuthUserRole;
import com.ai.domain.vo.Account;
import com.ai.service.AccountService;
import com.ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 22:04 2018/3/7
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthUserDao userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthUserRoleDao authUserRoleDao;

    @Autowired
    private AuthRoleMapper authRoleMapper;

    @Override
    public Account loadAccount(String appId) throws DataAccessException {
        AuthUser user = userMapper.selectByUniqueKey(appId);
        return user != null ? new Account(user.getUsername(),user.getPassword(),user.getSalt()) : null;
    }

    @Override
    public boolean isAccountExistByUid(String uid) {
        AuthUser user = userMapper.selectByPrimaryKey(uid);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(AuthUser account) throws DataAccessException {

        // 给新用户授权访客角色
        userService.authorityUserRole(account.getUid(),103);

        return userMapper.insertSelective(account) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public String loadAccountRole(String appId) throws DataAccessException {

        return userMapper.selectUserRoles(appId);
    }

    @Override
    public String loadAccountRoleId(String appId) throws DataAccessException {

        return userMapper.selectUserRolesId(appId);
    }

    @Override
    public boolean insertAuthUserRole(AuthUserRole aur) {
        return authUserRoleDao.insert(aur)==1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<AuthUserRole> selectAuthUserRoleByUserId(String userId) {
        return authUserRoleDao.selectAuthUserRoleByUserId(userId);
    }

    @Override
    public AuthUser getUserByUidAndPid(String uid, String pid) {
        return userMapper.getUserByUidAndPid(uid,pid);
    }
}
