package com.ai.dao;

import com.ai.domain.bo.AuthUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AuthUserDao extends AuthUserMapper{

    String selectUserRoles(String appId) throws DataAccessException;

    String selectUserRolesId(String appId) throws DataAccessException;

    AuthUser selectByUniqueKey(String appId) throws DataAccessException;

    List<AuthUser> selectUserList() throws DataAccessException;

    List<AuthUser> selectUserAndRoleList(String uid) throws DataAccessException;

    List<AuthUser> selectUserListByRoleId(Integer roleId) throws DataAccessException;

    List<AuthUser> selectUserListExtendByRoleId(Integer roleId) throws DataAccessException;

    AuthUser getUserByUidAndPid(@Param("uid") String uid , @Param("pid") String pid);

}