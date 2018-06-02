package com.ai.dao;

import com.ai.domain.bo.AuthRoleResource;

public interface AuthRoleResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRoleResource record);

    int insertSelective(AuthRoleResource record);

    AuthRoleResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthRoleResource record);

    int updateByPrimaryKey(AuthRoleResource record);
}