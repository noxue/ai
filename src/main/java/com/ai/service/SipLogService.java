package com.ai.service;

import com.ai.domain.bo.SipLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SipLogService {

    PageInfo<SipLog> findSipLog(int pageNum, int pageSize, String appId, String uid);

    boolean addSipLog(SipLog sipLogs);

}
