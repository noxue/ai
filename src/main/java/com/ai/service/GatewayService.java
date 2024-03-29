package com.ai.service;

import com.ai.domain.bo.Gateway;
import com.github.pagehelper.PageInfo;

public interface GatewayService {

    PageInfo<Gateway> findAllGate(int pageNum, int pageSize, String name, String user_id);

    PageInfo<Gateway> findGatewaysByAppId(int pageNum, int pageSize, String appId);

    boolean registerGate(Gateway gateway);

    boolean isGateExistByName(String name);

    boolean editGate(Gateway gateway);

    boolean delGate(long id);

    Gateway getGateById(long id);

}
