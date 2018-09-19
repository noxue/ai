package com.ai.dao;

import com.ai.domain.bo.AuthUserInfo;
import com.ai.domain.bo.AuthUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthUserInfoMapper {
    long countByExample(AuthUserInfoExample example);

    int deleteByExample(AuthUserInfoExample example);

    int deleteByPrimaryKey(String userId);

    int insert(AuthUserInfo record);

    int insertSelective(AuthUserInfo record);

    List<AuthUserInfo> selectByExample(AuthUserInfoExample example);

    AuthUserInfo selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") AuthUserInfo record, @Param("example") AuthUserInfoExample example);

    int updateByExample(@Param("record") AuthUserInfo record, @Param("example") AuthUserInfoExample example);

    int updateByPrimaryKeySelective(AuthUserInfo record);

    int updateByPrimaryKey(AuthUserInfo record);
}