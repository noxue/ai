package com.ai.dao;

import com.ai.domain.bo.Sip;

import java.util.List;

public interface SipDao extends SipMapper{

    //获取所有Sip
    List<Sip> getSipsList(String name);

    //根据name查找Sip
    Sip selectByName(String name);

}