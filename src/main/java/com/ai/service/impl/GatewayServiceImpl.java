package com.ai.service.impl;

import com.ai.dao.AppDao;
import com.ai.dao.AppMapper;
import com.ai.dao.GatewayDao;
import com.ai.dao.GatewayMapper;
import com.ai.domain.bo.App;
import com.ai.domain.bo.Gateway;
import com.ai.service.AppService;
import com.ai.service.GatewayService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GatewayService")
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    private GatewayDao gatewayDao;

    @Autowired
    private GatewayMapper gatewayMapper;

    @Override
    public PageInfo<Gateway> findAllGate(int pageNum, int pageSize, String uid) {
        PageHelper.startPage(pageNum, pageSize);
        List<Gateway> gatewaysList = gatewayDao.getGatewaysListByUid(uid);
        PageInfo result = new PageInfo(gatewaysList);
        return result;
    }

    @Override
    public boolean isGateExistByName(String name) {
        return false;
    }

    @Override
    public boolean registerGate(Gateway gateway) {
        return gatewayMapper.insert(gateway) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean editGate(Gateway gateway) {
        return gatewayMapper.updateByPrimaryKey(gateway) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean delGate(long id) {
        return gatewayMapper.deleteByPrimaryKey(id) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Gateway getGateById(long id) {
        return gatewayMapper.selectByPrimaryKey(id);
    }
}
