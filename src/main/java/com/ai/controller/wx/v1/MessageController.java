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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/* *
 * @Author ws
 */
@RestController
@RequestMapping("/wx/api/v1/message")
public class MessageController {

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
}
