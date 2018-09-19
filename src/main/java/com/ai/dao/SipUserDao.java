package com.ai.dao;

import com.ai.domain.bo.SipUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SipUserDao extends SipUserMapper{

    //获取所有SipUser
    List<SipUser> selectByUid(String user_id);

    int insertBatch(List<SipUser> list);

    SipUser isRepeat(@Param("user_id") String user_id, @Param("sip_id") String sip_id);

    int deleteBySipId(List<String> sip_id);

}