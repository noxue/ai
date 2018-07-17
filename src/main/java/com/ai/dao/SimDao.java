package com.ai.dao;

import com.ai.domain.bo.Sim;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SimDao extends SimMapper{

    //根据uid查找sim卡信息
    List<Sim> getSimsList(@Param("uid") String uid,@Param("phone")  String phone);
    //根据gatewayId查找sim卡信息
    List<Sim> getSimByGatewayId(long gatewayId);

    Sim getSimBySimIdAndUserId(String user_id , int sim_id);

    List<Sim> getSimUserList(String user_id);


}