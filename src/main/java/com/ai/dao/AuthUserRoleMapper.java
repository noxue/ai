package com.ai.dao;

import com.ai.domain.bo.AuthUserRole;

public interface AuthUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthUserRole record);

    int insertSelective(AuthUserRole record);

    AuthUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthUserRole record);

    int updateByPrimaryKey(AuthUserRole record);
}