package com.ai.service;

import com.ai.domain.bo.AuthUser;
import com.ai.domain.bo.AuthUserRole;
import com.ai.domain.vo.Account;

import java.util.List;

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
    String loadAccountRoleId(String appId);
    boolean insertAuthUserRole(AuthUserRole aur);
    //根据userid获取所有的AuthUserRole信息
    List<AuthUserRole> selectAuthUserRoleByUserId(String userId);
}
