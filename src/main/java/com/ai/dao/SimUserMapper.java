package com.ai.dao;

import com.ai.domain.bo.SimUser;

public interface SimUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SimUser record);

    int insertSelective(SimUser record);

    SimUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SimUser record);

    int updateByPrimaryKey(SimUser record);
}