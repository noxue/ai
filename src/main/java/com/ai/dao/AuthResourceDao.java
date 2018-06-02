package com.ai.dao;

import com.ai.domain.bo.AuthResource;
import com.ai.shiro.rule.RolePermRule;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AuthResourceDao extends AuthResourceMapper{

    List<RolePermRule> selectRoleRules()  throws DataAccessException;

    List<AuthResource> selectAuthorityMenusByUid(String appId) throws DataAccessException;

    List<AuthResource> selectMenus() throws DataAccessException;

    List<AuthResource> selectApiTeamList() throws DataAccessException;

    List<AuthResource> selectApiList() throws DataAccessException;

    List<AuthResource> selectApiListByTeamId(Integer teamId) throws DataAccessException;

    List<AuthResource> selectApisByRoleId(Integer roleId) throws DataAccessException;

    List<AuthResource> selectMenusByRoleId(Integer roleId) throws DataAccessException;

    List<AuthResource> selectNotAuthorityApisByRoleId(Integer roleId) throws DataAccessException;

    List<AuthResource> selectNotAuthorityMenusByRoleId(Integer roleId) throws DataAccessException;
}