package com.ai.service.impl;

import com.ai.dao.SipLogDao;
import com.ai.domain.bo.SipLog;
import com.ai.service.SipLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SipLogService")
public class SipLogServiceImpl implements SipLogService {

    @Autowired
    private SipLogDao sipLogDao;

    @Override
    public PageInfo<SipLog> findSipLog(int pageNum, int pageSize, String appId, String uid) {
        PageHelper.startPage(pageNum, pageSize);
        List<SipLog> sll = sipLogDao.selectSipLogs(appId, uid);
        PageInfo result = new PageInfo(sll);
        return result;
    }

    @Override
    public boolean addSipLog(SipLog sipLogs) {
        return sipLogDao.insert(sipLogs)>0 ? Boolean.TRUE : Boolean.FALSE;
    }
}
