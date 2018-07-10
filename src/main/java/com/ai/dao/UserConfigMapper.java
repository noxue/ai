package com.ai.dao;

import com.ai.domain.bo.UserConfig;

public interface UserConfigMapper {
    int insert(UserConfig record);

    int insertSelective(UserConfig record);
}