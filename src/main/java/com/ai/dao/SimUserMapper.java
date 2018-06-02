package com.ai.dao;

import com.ai.domain.bo.SimUser;

public interface SimUserMapper {
    int insert(SimUser record);

    int insertSelective(SimUser record);
}