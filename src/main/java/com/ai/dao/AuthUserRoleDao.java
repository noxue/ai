package com.ai.dao;

import com.ai.domain.bo.AuthUserRole;
import org.springframework.dao.DataAccessException;

public interface AuthUserRoleDao  extends AuthUserRoleMapper{

    int deleteByUniqueKey(AuthUserRole record) throws DataAccessException;
}