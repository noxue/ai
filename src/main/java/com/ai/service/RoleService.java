package com.ai.service;

import com.ai.domain.bo.AuthRole;

import java.util.List;

/* *
 * @Author tomsun28
 * @Description 
 * @Date 9:10 2018/3/20
 */
public interface RoleService {


    boolean authorityRoleResource(int roleId, int resourceId);

    boolean addRole(AuthRole role);

    boolean updateRole(AuthRole role);

    boolean deleteRoleByRoleId(Integer roleId);

    boolean deleteAuthorityRoleResource(Integer roleId, Integer resourceId);

    List<AuthRole> getRoleList();

    List<AuthRole> getRolesById(int id);
    //根据主键查询
    AuthRole selectByRoleId(String roleId);
}
