package com.ai.service.impl;

import com.ai.dao.AuthUserDao;
import com.ai.dao.AuthUserMapper;
import com.ai.dao.AuthUserRoleDao;
import com.ai.dao.AuthUserRoleMapper;
import com.ai.domain.bo.AuthUser;
import com.ai.domain.bo.AuthUserRole;
import com.ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 21:15 2018/3/17
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthUserDao userMapper;

    @Autowired
    private AuthUserRoleDao authUserRoleMapper;

    @Override
    public String loadAccountRole(String appId) throws DataAccessException {

        return userMapper.selectUserRoles(appId);
    }

    @Override
    public List<AuthUser> getUserList() throws DataAccessException {
        return userMapper.selectUserList();
    }

    @Override
    public List<AuthUser> getUserList(String pid,String uid) throws DataAccessException {
        if(StringUtils.isEmpty(uid)){
            uid = pid;
            return userMapper.selectUsers(pid,uid);
        }else{
            return userMapper.selectUserAndRoleList(pid ,uid);
        }
    }

    @Override
    public List<AuthUser> getUserListByRoleId(Integer roleId) throws DataAccessException {
        return userMapper.selectUserListByRoleId(roleId);
    }

    @Override
    public boolean authorityUserRole(String uid, int roleId) throws DataAccessException {
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleId(roleId);
        authUserRole.setUserId(uid);
        authUserRole.setCreateTime(new Date());
        authUserRole.setUpdateTime(new Date());
        return authUserRoleMapper.insert(authUserRole) == 1? Boolean.TRUE :Boolean.FALSE;
    }

    @Override
    public boolean deleteAuthorityUserRole(String uid, int roleId) throws DataAccessException {
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(uid);
        authUserRole.setRoleId(roleId);
        return authUserRoleMapper.deleteByUniqueKey(authUserRole) == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean deleteAuthRoleUserByUserId(String uid ) throws DataAccessException {
        return authUserRoleMapper.deleteByUserId(uid) == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public AuthUser getUserByAppId(String appId) throws DataAccessException {

        return userMapper.selectByUniqueKey(appId);
    }

    @Override
    public List<AuthUser> getNotAuthorityUserListByRoleId(Integer roleId) throws DataAccessException {

        return userMapper.selectUserListExtendByRoleId(roleId);
    }

    @Override
    public boolean delUser(String uid) {
        return userMapper.deleteByPrimaryKey(uid)== 1? Boolean.TRUE :Boolean.FALSE;
    }
}
