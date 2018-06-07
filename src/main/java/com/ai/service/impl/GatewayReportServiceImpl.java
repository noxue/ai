package com.ai.service.impl;

import com.ai.dao.GatewayDao;
import com.ai.dao.GatewayMapper;
import com.ai.dao.GatewayReportDao;
import com.ai.domain.bo.Gateway;
import com.ai.domain.bo.GatewayReport;
import com.ai.service.GatewayReportService;
import com.ai.service.GatewayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GatewayReportService")
public class GatewayReportServiceImpl implements GatewayReportService {

    @Autowired
    private GatewayReportDao gatewayReportDao;

    @Override
    public PageInfo<GatewayReport> findAllGatewayReport(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GatewayReport> gatewayReportssList = gatewayReportDao.getGatewayReportsList();
        PageInfo result = new PageInfo(gatewayReportssList);
        return result;
    }

    @Override
    public boolean registerGatewayReport(GatewayReport gatewayReport) {
        return gatewayReportDao.insert(gatewayReport) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delReport(long id) {
        return gatewayReportDao.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public GatewayReport getGatewayReoprtById(long id) {
        return gatewayReportDao.selectByPrimaryKey(id);
    }
}
