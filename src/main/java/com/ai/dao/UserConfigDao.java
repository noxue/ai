package com.ai.dao;

import com.ai.domain.bo.UserConfig;
import org.apache.ibatis.annotations.Param;

public interface UserConfigDao extends UserConfigMapper{

    UserConfig selectByUserId(@Param("userId") String userId ,@Param("key") String key);

    int updateConfig(UserConfig userConfig);

}