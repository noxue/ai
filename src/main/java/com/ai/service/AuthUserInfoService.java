package com.ai.service;

import com.ai.domain.bo.AuthUserInfo;

public interface AuthUserInfoService {

    AuthUserInfo getUserById(String uid);

    boolean addUser(AuthUserInfo uid);

    boolean editUser(String userId,  int threadNum);
}
