package com.ai.controller.wx.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.common.message.Article;
import com.riversoft.weixin.common.message.Media;
import com.riversoft.weixin.common.message.News;
import com.riversoft.weixin.common.message.Video;
import com.riversoft.weixin.common.message.xml.*;
import com.riversoft.weixin.mp.message.MpMessages;
import com.riversoft.weixin.mp.message.MpXmlMessages;
import com.riversoft.weixin.mp.message.xml.Forward2CareXmlMessage;
import com.riversoft.weixin.mp.template.Data;
import com.riversoft.weixin.mp.template.Templates;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.util.*;


/* *
 * @Author ws
 */
@RestController
@RequestMapping("/wx/api/v1/template")
public class WXTemplateController {

    public void testSend(){
        List<String> users = new ArrayList<>();
        users.add("o7Tmfs3EHCndVenva5knKxA4D3XA");
        users.add("o7Tmfs96UDesd920Gzi0jYJPnBzQ");
        MpMessages.defaultMpMessages().mpNews(users, "oR82Fct6wNYgjjFMQVNdmi089sdyuSVtovm1DFqG0Wg");
    }


    public void testSendAll(){
        long msgId = MpMessages.defaultMpMessages().mpNews("oR82Fct6wNYgjjFMQVNdmi089sdyuSVtovm1DFqG0Wg");
        boolean result = MpMessages.defaultMpMessages().success(msgId);
        System.out.println(result);
    }

    @PostMapping("/sendAll")
    public void testTextToXML() throws JsonProcessingException {
        TextXmlMessage textXmlMessage = new TextXmlMessage();
        textXmlMessage.setContent("content");
        textXmlMessage.setFromUser("from user");
        textXmlMessage.setToUser("to user");
        textXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(textXmlMessage);
        xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>");
        xml.contains("<FromUserName><![CDATA[from user]]>");
        xml.contains("<MsgType><![CDATA[text]]></MsgType>");
        xml.contains("<Content><![CDATA[content]]></Content>");
        System.out.println(xml);
    }


    public void testImageToXML() throws JsonProcessingException {
        ImageXmlMessage imageXmlMessage = new ImageXmlMessage();
        imageXmlMessage.setMedia(new Media("media-id"));
        imageXmlMessage.setFromUser("from user");
        imageXmlMessage.setToUser("to user");
        imageXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(imageXmlMessage);
        xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>");
        xml.contains("<FromUserName><![CDATA[from user]]>");
        xml.contains("<MsgType><![CDATA[image]]></MsgType>");
        xml.contains("<Image><MediaId>media-id</MediaId></Image>");

    }

    public void testVoiceToXML() throws JsonProcessingException {
        VoiceXmlMessage voiceXmlMessage = new VoiceXmlMessage();
        voiceXmlMessage.setMedia(new Media("media-id"));
        voiceXmlMessage.setFromUser("from user");
        voiceXmlMessage.setToUser("to user");
        voiceXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(voiceXmlMessage);
        xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>");
        xml.contains("<FromUserName><![CDATA[from user]]>");
        xml.contains("<MsgType><![CDATA[voice]]></MsgType>");
        xml.contains("<Voice><MediaId>media-id</MediaId></Voice>");
    }

    public void testVideoToXML() throws JsonProcessingException {
        VideoXmlMessage videoXmlMessage = new VideoXmlMessage();

        Video video = new Video();
        video.setMediaId("video media id");
        video.setTitle("title");
        video.setDescription("desc");
        videoXmlMessage.setVideo(video);
        videoXmlMessage.setFromUser("from user");
        videoXmlMessage.setToUser("to user");
        videoXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(videoXmlMessage);
        xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>");
        xml.contains("<FromUserName><![CDATA[from user]]>");
        xml.contains("<MsgType><![CDATA[video]]></MsgType>");
        xml.contains("<Video><MediaId><![CDATA[video media id]]></MediaId><Title><![CDATA[title]]></Title><Description><![CDATA[desc]]></Description></Video>");
    }

    public void testNewsToXML() throws JsonProcessingException {
        NewsXmlMessage newsXmlMessage = new NewsXmlMessage();

        News news = new News();
        Article article1 = new Article();
        article1.setTitle("[1]测试news");
        article1.setDescription("[1]今日头条，正在调试message API, 测试是否能正常发送news类型。");
        article1.setUrl("http://riversoft.com.cn/Upload/Pic/banner4.jpg");
        article1.setPicUrl("http://riversoft.com.cn/Upload/Pic/banner2.jpg");
        news.add(article1);

        Article article2 = new Article();
        article2.setTitle("[2]测试news");
        article2.setDescription("[2]今日头条，正在调试message API, 测试是否能正常发送news类型。");
        article2.setUrl("http://riversoft.com.cn/Upload/Pic/banner4.jpg");
        article2.setPicUrl("http://riversoft.com.cn/Upload/Pic/banner2.jpg");
        news.add(article2);

        newsXmlMessage.setNews(news);
        newsXmlMessage.setFromUser("from user");
        newsXmlMessage.setToUser("to user");
        newsXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(newsXmlMessage);
        xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>");
        xml.contains("<FromUserName><![CDATA[from user]]>");
        xml.contains("<MsgType><![CDATA[news]]></MsgType>");
        xml.contains("<ArticleCount>2</ArticleCount>");
        xml.contains("<Articles><item><Title><![CDATA[[1]测试news]]></Title>");
    }

    public void testKfMessageToXML() throws JsonProcessingException {
        Forward2CareXmlMessage kfXmlMessage = new Forward2CareXmlMessage();
        kfXmlMessage.setFromUser("from user");
        kfXmlMessage.setToUser("to user");
        kfXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(kfXmlMessage);
        xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>");
        xml.contains("<FromUserName><![CDATA[from user]]>");

        kfXmlMessage.setAccount("kfaccount");
        xml = MpXmlMessages.toXml(kfXmlMessage);
        xml.contains("<TransInfo><KfAccount><![CDATA[kfaccount]]></KfAccount>");
    }

    @PostMapping("/sendt")
    public void testSendTemplate(){
        String openId = "oELhlt7Q-lRmLbRsPsaKeVX6pqjg";
        String templateId = "sGy7O4ZbXfzF1suGoCR0Gst1IQNe0df5ewTZy7NaR9g";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx74755fbba35c24da&redirect_uri=http%3A%2F%2Fwxtest.gzriver.com%2Fcss%2Fthemes%2Fsmoothness%2Fjquery-ui.min.css&response_type=code&scope=snsapi_userinfo&state=state111#wechat_redirect";
        Map<String, Data> data = new HashMap<>();
        data.put("productType", new Data("商品名", "#173177"));
        data.put("name", new Data("微信某某店某商品", "#173177"));
        data.put("accountType", new Data("会员卡号", "#173177"));
        data.put("account", new Data("11912345678", "#173177"));
        data.put("time", new Data("2013年8月20日 20:38", "#173177"));
        data.put("remark", new Data("您可以回复文字或语音对该商品及商家进行评价哦~", "#173177"));

        long messageId = Templates.defaultTemplates().send(openId, templateId, url, data);
        System.out.println(messageId);

    }

}
