package com.ai.service;

import com.ai.domain.bo.AuthUser;

import java.util.List;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 21:14 2018/3/17
 */
public interface UserService {

    String loadAccountRole(String appId);

    List<AuthUser> getUserList();

    List<AuthUser> getUserList(String name);

    List<AuthUser> getUserListByRoleId(Integer roleId);

    boolean authorityUserRole(String appId, int roleId);

    boolean deleteAuthorityUserRole(String uid,int roleId);

    boolean deleteAuthRoleUserByUserId(String uid);

    AuthUser getUserByAppId(String appId);

    List<AuthUser> getNotAuthorityUserListByRoleId(Integer roleId);

    boolean delUser(String uid);
}
