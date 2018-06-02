package com.ai.dao;

import com.ai.domain.bo.Gateway;

public interface GatewayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gateway record);

    int insertSelective(Gateway record);

    Gateway selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Gateway record);

    int updateByPrimaryKey(Gateway record);
}