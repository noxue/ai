package com.ai.test;

import com.ai.domain.bo.wx.BaseButton;
import com.ai.domain.bo.wx.ComplexButton;
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
        WX_TokenUtil token = new WX_TokenUtil();
        String accesstoken = token.getWXToken().getAccessToken();
        System.out.println("accesstoken:" + accesstoken);
        ViewButton cbtn1 = new ViewButton();
        cbtn1.setName("账号绑定");
        cbtn1.setType("view");
//        String redurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
//                "wx5cca7d43b7351114" +
//                "&redirect_uri=" +
//                WX_CommonUtil.urlEncodeUTF8("http://crm.ai-xg.com/wx/api/v1/user/redirectUrl") +
//                        "&response_type=code&scope=snsapi_base&state=1&connect_redirect=1#wechat_redirect";
        //"wx354bcb423c2dd062";
        String redurl ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
                "wx354bcb423c2dd062" +
                "&redirect_uri=" +
                WX_CommonUtil.urlEncodeUTF8("http://wshw3m.natappfree.cc/wx/api/v1/user/unSilent")+
                "&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
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
        ViewButton bace1 = new ViewButton();
        bace1.setName("产品介绍");
        bace1.setType("view");
        String bace1Url = "https://mp.weixin.qq.com/s/UcgIPM8JlXSVlAO1WWS7Tw";
        bace1.setUrl(bace1Url);

        ComplexButton introBtn = new ComplexButton();
        introBtn.setName("公司介绍");
        introBtn.setSub_button(new BaseButton[]{bace1});


        ViewButton Speech1 = new ViewButton();
        Speech1.setName("房地产录音");
        Speech1.setType("view");
        String Speech1Url = "https://mp.weixin.qq.com/s/XTvQ9l-2-ArvoXHJrNVBoA";
        Speech1.setUrl(Speech1Url);

        ViewButton Speech2 = new ViewButton();
        Speech2.setName("催收机器人");
        Speech2.setType("view");
        String Speech2Url = "https://mp.weixin.qq.com/s/ivaPKV2Tp30NctuGe3Q0sg";
        Speech2.setUrl(Speech2Url);

        ViewButton Speech3 = new ViewButton();
        Speech3.setName("装修机器人");
        Speech3.setType("view");
        String Speech3Url = "https://mp.weixin.qq.com/s/Wv1JGPXx5ML7fWyuN2JJCw";
        Speech3.setUrl(Speech3Url);

        ViewButton Speech4 = new ViewButton();
        Speech4.setName("贷款机器人");
        Speech4.setType("view");
        String Speech4Url = "https://mp.weixin.qq.com/s/C1Q2ouXnZkJ3at2A8EhLlw";
        Speech4.setUrl(Speech4Url);

        ViewButton Speech5 = new ViewButton();
        Speech5.setName("移动增值");
        Speech5.setType("view");
        String Speech5Url = "https://mp.weixin.qq.com/s/xJe7FPtbD0N9SMsRY9SJBg";
        Speech5.setUrl(Speech5Url);



        ComplexButton Speech = new ComplexButton();
        Speech.setName("语音试听");
        Speech.setSub_button(new BaseButton[]{Speech1,Speech2,Speech3,Speech4,Speech5});
        //菜单对象
        Menu menu = new Menu();
//        menu.setButton(new BaseButton[]{cbtn1,vbtn});
        menu.setButton(new BaseButton[]{introBtn,Speech,cbtn1});
        //将Java对象转换成json字符串      JSONObject.fromObject(menu);//将字符串转换成Java对象
        String json = JSONObject.fromObject(menu).toString();
//		System.out.println(json);
        //String  accesstoken= "13_F8pkru9z_7tSEp2iqfNeCrl2ahzGQ_1di0nNqQu8cqy5SuRjbH8UfTBOvFweeok60gJmLugrbisBuKOn2lzCzqs4diHrqE0eevbMWNYlwk8Gu2on31HMlh4IDMQVQHaACACXO";
        MenuUtil.createMenu(json, accesstoken);

//		MenuUtil.delMenu(accesstoken);

    }

}
