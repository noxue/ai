package com.ai.service.impl;

import com.ai.dao.UserConfigDao;
import com.ai.domain.bo.UserConfig;
import com.ai.service.UserConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserConfigService")
public class UserConfigServiceImpl implements UserConfigService {

    @Autowired
    private UserConfigDao userConfigDao;

    @Override
    public UserConfig getConfigByUserId(String userId,String key) {
        return userConfigDao.selectByUserId(userId,"schedule");
    }

    @Override
    public boolean insertConfig(UserConfig record) {
        return userConfigDao.insert(record) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editConfig(UserConfig record) {
        return userConfigDao.updateConfig(record) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }


}
