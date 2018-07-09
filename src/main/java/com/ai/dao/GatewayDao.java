package com.ai.dao;

import com.ai.domain.bo.Gateway;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GatewayDao extends GatewayMapper{

    //根据uid查找网关信息
    List<Gateway> getGatewaysListByUid(@Param("name") String name, @Param("user_id")String user_id);

    //根据uid查找网关信息
    List<Gateway> getGatewaysByAppId(@Param("appId") int appId);

}