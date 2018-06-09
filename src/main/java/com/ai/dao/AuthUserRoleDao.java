package com.ai.dao;

import com.ai.domain.bo.AuthUserRole;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AuthUserRoleDao  extends AuthUserRoleMapper{

    int deleteByUniqueKey(AuthUserRole record) throws DataAccessException;

    List<AuthUserRole> selectAuthUserRoleByUserId(String userId);
}