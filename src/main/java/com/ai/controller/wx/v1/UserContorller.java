package com.ai.controller.wx.v1;

import com.ai.domain.bo.wx.SNSUserInfo;
import com.ai.domain.bo.wx.WechatConfig;
import com.ai.domain.bo.wx.WeixinOauth2Token;
import com.ai.util.wx.AdvancedUtil;
import com.ai.util.wx.WX_HttpsUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/wx/api/v1/user")
public class UserContorller {

    @Autowired
    private WechatConfig wechatConfig;

    @ApiOperation(value = "静默式获取code", notes = "redirect_url微信返回访问的地址")
    @ResponseBody
    @RequestMapping(value = "/redirectUrl", method = {RequestMethod.GET, RequestMethod.POST})
    public void redirectLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取code
        String code = request.getParameter("code");
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

        requestUrl = requestUrl.replace("APPID", wechatConfig.getAppId());
        requestUrl = requestUrl.replace("SECRET",wechatConfig.getAppSecret());
        requestUrl = requestUrl.replace("CODE", code);
        JSONObject jsonObject = WX_HttpsUtil.httpsRequest(requestUrl,"GET",null);
        String openid = jsonObject.getString("openid");
        // http://127.0.0.1:9527/#/weChat
        response.sendRedirect("http://crm.ai-xg.com/#/weChat?contNo="+openid);
    }

    @ApiOperation(value = "解绑", notes = "微信返回访问的地址")
    @ResponseBody
    @RequestMapping(value = "/unBanding", method = {RequestMethod.GET, RequestMethod.POST})
    public void unBanding(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取code
        String code = request.getParameter(" code");
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

        requestUrl = requestUrl.replace("APPID", wechatConfig.getAppId());
        requestUrl = requestUrl.replace("SECRET",wechatConfig.getAppSecret());
        requestUrl = requestUrl.replace("CODE", code);
        JSONObject jsonObject = WX_HttpsUtil.httpsRequest(requestUrl,"GET",null);
        String openid = jsonObject.getString("openid");

    }

    @ApiOperation(value = "非静默式获取code,v2.0", notes = "redirect_url微信返回访问的地址,v2.0")
    @ResponseBody
    @RequestMapping(value = "/unSilent", method = {RequestMethod.GET, RequestMethod.POST})
    public void unSilent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取code
        String openId ="";
        String code = request.getParameter("code");
        if(null != code){
            WeixinOauth2Token wot = AdvancedUtil.getOAuth2Token("wx354bcb423c2dd062", "0801246c4cb5126b633234700763165e", code);
            System.out.println("用户的OpenId：" + wot.getOpenid());

            openId = wot.getOpenid();
            String codeAccessToken = wot.getAccess_token();
            //获取用户基本信息
            SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(codeAccessToken, openId);
            System.out.println("昵称：" + snsUserInfo.getNickname());

            request.setAttribute("openId", wot.getOpenid());
            request.setAttribute("nickname", snsUserInfo.getNickname());
            request.setAttribute("sex", snsUserInfo.getSex());
            request.setAttribute("headimgurl", snsUserInfo.getHeadimgurl());
            request.setAttribute("city", snsUserInfo.getCity());

            System.out.println("wot.getOpenid():" + wot.getOpenid());
            System.out.println("snsUserInfo.getNickname():" + snsUserInfo.getNickname());
            System.out.println("snsUserInfo.getSex():" + snsUserInfo.getSex());
            System.out.println("snsUserInfo.getHeadimgurl():" + snsUserInfo.getHeadimgurl());
            System.out.println("snsUserInfo.getCity():" + snsUserInfo.getCity());
        }

        response.sendRedirect("http://crm.ai-xg.com/#/weChat?contNo="+openId);
    }

}