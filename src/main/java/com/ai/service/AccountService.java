package com.ai.service;

import com.ai.domain.bo.AuthUser;
import com.ai.domain.vo.Account;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 22:02 2018/3/7
 */
public interface AccountService {

    Account loadAccount(String appId);
    boolean isAccountExistByUid(String uid);
    boolean registerAccount(AuthUser account);
    String loadAccountRole(String appId);
}
