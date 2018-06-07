package com.ai.dao;

import com.ai.domain.bo.GatewayReport;

import java.util.List;

public interface GatewayReportDao extends GatewayReportMapper{

    List<GatewayReport> getGatewayReportsList();

}