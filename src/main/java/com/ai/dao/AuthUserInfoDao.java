package com.ai.dao;

import org.apache.ibatis.annotations.Param;

public interface AuthUserInfoDao extends AuthUserInfoMapper{

    int updateAuther(@Param("userId") String userId, @Param("threadNum") int threadNum);

}