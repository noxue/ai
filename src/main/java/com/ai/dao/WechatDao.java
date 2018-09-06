package com.ai.dao;

import com.ai.domain.bo.Wechat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WechatDao extends WechatMapper{

    List<Wechat> selectByUid(String uid);

    Wechat getByOpenid(@Param("uid")String uid ,@Param("openid") String openid);

}