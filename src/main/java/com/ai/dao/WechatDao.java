package com.ai.dao;

import com.ai.domain.bo.Wechat;

import java.util.List;

public interface WechatDao extends WechatMapper{

    List<Wechat> selectByUid(String uid);

}