package com.ai.dao;

import com.ai.domain.bo.AuthResource;

public interface AuthResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthResource record);

    int insertSelective(AuthResource record);

    AuthResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthResource record);

    int updateByPrimaryKey(AuthResource record);
}