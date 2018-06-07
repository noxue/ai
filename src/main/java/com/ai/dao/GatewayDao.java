package com.ai.dao;

import com.ai.domain.bo.Gateway;

import java.util.List;

public interface GatewayDao extends GatewayMapper{

    //根据uid查找网关信息
    List<Gateway> getGatewaysList(String uid);

}