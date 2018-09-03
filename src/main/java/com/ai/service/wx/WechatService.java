package com.ai.service.wx;


import com.ai.domain.bo.Wechat;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface WechatService {

    boolean isBanding(Wechat wechat);

    List<String> getOpenid(String uid);

    void senMsg(String openId,List<String> atten);

    String getAccessToken();

    String sendWechatMsgToUser(String touser, String templatId, String clickurl, String topcolor, JSONObject data , String accessToken);

    // boolean unBanding(String openid);

}
