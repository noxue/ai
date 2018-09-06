package com.ai.test;

import com.ai.domain.bo.wx.BaseButton;
import com.ai.domain.bo.wx.Menu;
import com.ai.domain.bo.wx.ViewButton;
import com.ai.util.wx.MenuUtil;
import com.ai.util.wx.WX_CommonUtil;
import com.ai.util.wx.WX_TokenUtil;
import net.sf.json.JSONObject;

public class test {


    /**
     * @param args
     */
    public static void main(String[] args) {
        //String accesstoken = CommonUtil.getCurrentAccessToken();
//        WX_TokenUtil token = new WX_TokenUtil();
//        String accesstoken = token.getWXToken().getAccessToken();
//        System.out.println("accesstoken:" + accesstoken);
        ViewButton cbtn1 = new ViewButton();
        cbtn1.setName("账号绑定");
        cbtn1.setType("view");
        String redurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
                "wx5cca7d43b7351114" +
                "&redirect_uri=" +
                WX_CommonUtil.urlEncodeUTF8("http://crm.ai-xg.com/wx/api/v1/user/redirectUrl") +
                        "&response_type=code&scope=snsapi_base&state=1&connect_redirect=1#wechat_redirect";
        cbtn1.setUrl(redurl);
//		ClickButton cbtn2 = new ClickButton();
//		cbtn2.setName("每日歌曲");
//		cbtn2.setType("click");
//		cbtn2.setKey("KEY_MUSIC");

//        ViewButton vbtn = new ViewButton();
//        vbtn.setName("百度搜索");
//        vbtn.setType("view");
//        vbtn.setUrl("http://www.baidu.com/");

//		ComplexButton btn = new ComplexButton();
//		btn.setName("菜单");
//		btn.setSub_button(new BaseButton[]{cbtn2,vbtn});

        //菜单对象
        Menu menu = new Menu();
//        menu.setButton(new BaseButton[]{cbtn1,vbtn});
        menu.setButton(new BaseButton[]{cbtn1});
        //将Java对象转换成json字符串      JSONObject.fromObject(menu);//将字符串转换成Java对象
        String json = JSONObject.fromObject(menu).toString();
//		System.out.println(json);
String  accesstoken= "13_uXl8CvEGJnddiuw28vkOY-QGKzDo_pvEDZBuwEFl2ESQcW02T4PhEliGYtkhWj8l5AF3sgXU_URYtAwPGd9Vg7BjCp8g9xfScapIIX5XTptv-slX4Yax4m-MTcZ-vh_RtxdpYoVpfdYKpRYgFYZfADAMFG";
        MenuUtil.createMenu(json, accesstoken);

//		MenuUtil.delMenu(accesstoken);

    }

}
