package com.ai.dao;

import com.ai.domain.bo.AuthRoleResource;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface AuthRoleResourceDao extends AuthRoleResourceMapper {

    int deleteByUniqueKey(@Param("roleId") Integer roleId, @Param("resourceId") Integer resourceId) throws DataAccessException;
}