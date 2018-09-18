package com.ai.service;

import com.ai.domain.bo.Sip;
import com.github.pagehelper.PageInfo;

public interface SipService {

    PageInfo<Sip> findAllSip(int pageNum, int pageSize, String name);

    boolean registerSip(Sip sip);

    boolean isSipExistByName(String name);

    boolean isSipExistByName(String name, String id);

    boolean editSip(Sip sip);

    boolean delSip(long id);

    Sip getSipById(long id);

}
