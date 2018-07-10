package com.ai.service;

import com.ai.domain.bo.UserConfig;

public interface UserConfigService {

    UserConfig getConfigByUserId(String userId,String key);

    boolean insertConfig(UserConfig record);

    boolean editConfig(UserConfig record);
}
