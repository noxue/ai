package com.ai.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ai.domain.bo.wx.TemplateData;
import com.ai.util.wx.WX_HttpsUtil;
import com.ai.util.wx.WX_TemplateMsgUtil;
import com.ai.util.wx.WX_TokenUtil;
import com.ai.util.wx.WX_UserUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class testTemplate {

    public static void main(String[] args) {
        //新增用户成功 - 推送微信消息
        senMsg("oWuwU1q6LmjKSJdygOikPUSNxQAc");
//        Integer  state= WX_UserUtil.subscribeState("oWuwU1q6LmjKSJdygOikPUSNxQAc");
//        System.out.println(state);

    }
    static void senMsg(String openId){
        //用户是否订阅该公众号标识 (0代表此用户没有关注该公众号 1表示关注了该公众号)
        Integer  state= WX_UserUtil.subscribeState(openId);
//        System.out.println("state:"+state);
        // 绑定了微信并且关注了服务号的用户 , 注册成功-推送注册短信
        if(state==1){
            Map<String,TemplateData> param = new HashMap<>();
            param.put("first",new TemplateData(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date()),"#696969"));
            param.put("keyword1",new TemplateData("国际旅游","#696969"));
            param.put("keyword2",new TemplateData("1171RMB","#696969"));
            param.put("keyword3",new TemplateData("2018年10月1日 8时30分00秒","#696969"));
            param.put("keyword4",new TemplateData("50人！","#696969"));
            param.put("remark",new TemplateData("祝您旅途愉快","#696969"));

            //注册的微信-模板Id
            // String regTempId =  WX_TemplateMsgUtil.getWXTemplateMsgId("ywBb70467vr18");

            JSON.toJSONString(param);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(param));
            //调用发送微信消息给用户的接口
//            WX_TemplateMsgUtil.sendWechatMsgToUser(openId,"F8yqj-vV-bkFH0A7lh3ifxuOwF19FSJT2RQXfeAjPMg", "www.baidu.com",
//                    "#000000", jsonObject);


//            //获取公众号的自动回复规则
//            String urlinfo="https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token="+WX_TokenUtil.getWXToken().getAccessToken();
//            JSONObject joinfo = WX_HttpsUtil.httpsRequest(urlinfo, "GET", null);
//            Object o=joinfo.get("is_add_friend_reply_open");
//            System.out.println("o:"+joinfo);
//            String getTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
//            JSONObject Token = WX_HttpsUtil.httpsRequest(getTokenUrl, "GET", null);
//            System.out.println("Token:"+Token);
        }
    }


}
