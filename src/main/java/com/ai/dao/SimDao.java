package com.ai.dao;

import com.ai.domain.bo.Sim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SimDao extends SimMapper{

    //根据uid查找网关信息
    List<Sim> getSimsList(@Param("uid") String uid,@Param("phone")  String phone);

    Sim getSimBySimIdAndUserId(String user_id , int sim_id);


}