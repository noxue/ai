package com.ai.service;

import com.ai.domain.bo.GatewayReport;
import com.github.pagehelper.PageInfo;

public interface GatewayReportService {

    PageInfo<GatewayReport> findAllGatewayReport(int pageNum, int pageSize);

    boolean registerGatewayReport(GatewayReport gatewayReport);

    boolean delReport(long id);

    GatewayReport getGatewayReoprtById(long id);

}
