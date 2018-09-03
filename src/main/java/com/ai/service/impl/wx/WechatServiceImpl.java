package com.ai.service.impl.wx;

import com.ai.dao.WechatDao;
import com.ai.domain.bo.Wechat;
import com.ai.domain.bo.wx.AccessToken;
import com.ai.domain.bo.wx.TemplateData;
import com.ai.domain.bo.wx.WechatConfig;
import com.ai.service.wx.WechatService;
import com.ai.util.wx.WX_HttpsUtil;
import com.ai.util.wx.WX_TemplateMsgUtil;
import com.ai.util.wx.WX_UserUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    private WechatDao wechatDao;

    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean isBanding(Wechat wechat) {
        return wechatDao.insert(wechat) ==1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<String> getOpenid(String uid) {
        List<String> openids = new ArrayList<>();
        List<Wechat> wechats =  wechatDao.selectByUid(uid);
        for(int i=0; i< wechats.size();i++ ){
            openids.add(wechats.get(i).getOpenid());
        }
        return openids;
    }

    @Override
    public void senMsg(String openId,List<String> atten) {
        //用户是否订阅该公众号标识 (0代表此用户没有关注该公众号 1表示关注了该公众号)
        //Integer  state= WX_UserUtil.subscribeState(openId);
        // 对关注了服务号的用户进行推送模板消息
        //if(state==1){
            Map<String,TemplateData> param = new HashMap<>();
            //param.put("first",new TemplateData("以下是我们根据您关注的用户类型而筛选的意向客户","#696969"));
            param.put("keyword1",new TemplateData(atten.get(0),"#286bb2"));
            param.put("keyword2",new TemplateData(atten.get(1),"#286bb2"));
            param.put("keyword3",new TemplateData(new SimpleDateFormat("HH:mm:ss").format(new Date()),"#286bb2"));
            param.put("keyword4",new TemplateData(atten.get(2),"#286bb2"));
            //param.put("remark",new TemplateData("点我查看更多详情,如有疑问请联系110","#696969"));

            //注册的微信-模板Id
            // String regTempId =  WX_TemplateMsgUtil.getWXTemplateMsgId("ywBb70467vr18");
            JSON.toJSONString(param);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(param));

            String accessToken = redisTemplate.opsForValue().get("access_token");
            System.out.println("accessToken1:" + accessToken);
            //调用发送微信消息给用户的接口
            String result = sendWechatMsgToUser(openId,"xMO4W9kvGHay4kmCyj7CSGANkfuwxqelN1JTftndusw", "http://crm.ai-xg.com/#/weChat/taskDetail?id="+atten.get(3),
                    "#000000", jsonObject , accessToken);
            if(!"success".equals(result)){
                sendWechatMsgToUser(openId,"xMO4W9kvGHay4kmCyj7CSGANkfuwxqelN1JTftndusw", "http://crm.ai-xg.com/#/weChat/taskDetail?id="+atten.get(3),
                        "#000000", jsonObject , getAccessToken());
            }
        //}
    }

    @Override
    public String getAccessToken() {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + wechatConfig.getAppId()
                +"&secret="+ wechatConfig.getAppSecret();
        JSONObject jsonObject = WX_HttpsUtil.httpsRequest(tokenUrl, "GET", null);
        System.out.println("jsonObject:"+jsonObject);
        AccessToken access_token = new AccessToken();
        if (null != jsonObject) {
            try {
                access_token.setAccessToken(jsonObject.getString("access_token"));
                access_token.setExpiresin(jsonObject.getInteger("expires_in"));
            } catch (JSONException e) {
                access_token = null;
                // 获取token失败
                //log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }
        redisTemplate.opsForValue().set("access_token", access_token.getAccessToken());
        return access_token.getAccessToken();
    }

    /**
     * 发送微信消息(模板消息)
     * @param touser 用户 OpenID
     * @param templatId 模板消息ID
     * @param clickurl URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）。
     * @param topcolor 标题颜色
     * @param data 详细内容
     * @return
     */
    public String sendWechatMsgToUser(String touser, String templatId, String clickurl, String topcolor, JSONObject data ,String accessToken) {

        String tmpurl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+ accessToken;
        JSONObject json = new JSONObject();
        json.put("touser", touser);
        json.put("template_id", templatId);
        json.put("url", clickurl);
        json.put("topcolor", topcolor);
        json.put("data", data);
        try{
            JSONObject result = WX_HttpsUtil.httpsRequest(tmpurl, "POST", json.toString());
            JSONObject resultJson = new JSONObject(result);
            //log.info("发送微信消息返回信息：" + resultJson.get("errcode"));
            String errmsg = (String) resultJson.get("errmsg");
            if(!"ok".equals(errmsg)){  //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
                return "error";
            }
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }finally {
//            if(templatId!=null) {
//                //删除新增的 微信模板
//                deleteWXTemplateMsgById(templatId);
//            }
        }
        return "success";
    }
}
