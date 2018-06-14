package com.ai.dao;

import com.ai.domain.bo.Sim;

import java.util.List;

public interface SimDao extends SimMapper{

    //根据uid查找网关信息
    List<Sim> getSimsList(String uid);

    Sim getSimBySimIdAndUserId(String user_id , int sim_id);


}