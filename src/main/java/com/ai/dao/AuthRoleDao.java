package com.ai.dao;

import com.ai.domain.bo.AuthRole;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AuthRoleDao extends AuthRoleMapper {

    List<AuthRole> selectRoles() throws DataAccessException;

    List<AuthRole> selectRolesById(int id);
}