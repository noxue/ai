package com.ai.controller.wx.v1;

import com.ai.domain.bo.wx.WechatConfig;
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

    @ApiOperation(value = "获取code", notes = "redirect_url微信返回访问的地址")
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



}