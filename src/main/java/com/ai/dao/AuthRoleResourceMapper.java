package com.ai.dao;

import com.ai.domain.bo.AuthRoleResource;
import com.ai.domain.bo.AuthRoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthRoleResourceMapper {
    long countByExample(AuthRoleResourceExample example);

    int deleteByExample(AuthRoleResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AuthRoleResource record);

    int insertSelective(AuthRoleResource record);

    List<AuthRoleResource> selectByExample(AuthRoleResourceExample example);

    AuthRoleResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AuthRoleResource record, @Param("example") AuthRoleResourceExample example);

    int updateByExample(@Param("record") AuthRoleResource record, @Param("example") AuthRoleResourceExample example);

    int updateByPrimaryKeySelective(AuthRoleResource record);

    int updateByPrimaryKey(AuthRoleResource record);
}