package com.ai.service;

import com.ai.domain.bo.Sip;
import com.ai.domain.bo.SipUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SipUserService {

    PageInfo<SipUser> findAllSipUser(int pageNum, int pageSize, String user_id);

    List<Sip> findAllSipUser(String user_id);

    List<SipUser> findSipUser(String user_id);

    boolean registerSipUser(SipUser sipUser);

    boolean editSipUser(SipUser sipUser);

    boolean delSipUser(long id);

    SipUser getSipUserById(long id);

    boolean insertSipUserList(List<SipUser> list);

    boolean delSipUserList(List<String> Sip_id);

    boolean checkRepeat(String user_id,String sip_id);



}
