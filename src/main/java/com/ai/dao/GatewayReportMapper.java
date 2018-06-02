package com.ai.dao;

import com.ai.domain.bo.GatewayReport;

public interface GatewayReportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GatewayReport record);

    int insertSelective(GatewayReport record);

    GatewayReport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GatewayReport record);

    int updateByPrimaryKeyWithBLOBs(GatewayReport record);

    int updateByPrimaryKey(GatewayReport record);
}