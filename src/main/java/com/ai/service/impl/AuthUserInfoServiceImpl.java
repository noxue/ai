package com.ai.service.impl;

import com.ai.dao.AuthUserInfoDao;
import com.ai.dao.AuthUserInfoMapper;
import com.ai.domain.bo.AuthUserInfo;
import com.ai.service.AuthUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AuthUserInfoService")
public class AuthUserInfoServiceImpl implements AuthUserInfoService {

    @Autowired
    private AuthUserInfoDao authUserInfoDao;

    @Override
    public AuthUserInfo getUserById(String uid) {
        return authUserInfoDao.selectByPrimaryKey(uid);
    }

    @Override
    public boolean addUser(AuthUserInfo authUserInfo) {
        return authUserInfoDao.insert(authUserInfo)==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editUser( String userId,  int threadNum) {
        return authUserInfoDao.updateAuther(userId,threadNum)==1 ? Boolean.TRUE : Boolean.FALSE;
    }
}
