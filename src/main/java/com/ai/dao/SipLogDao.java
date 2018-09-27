package com.ai.dao;

import com.ai.domain.bo.SipLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SipLogDao extends  SipLogMapper{

    List<SipLog> selectSipLogs(@Param("appId") String appId,@Param("uid") String uid);

    int insertBatch(List<SipLog> list);
}