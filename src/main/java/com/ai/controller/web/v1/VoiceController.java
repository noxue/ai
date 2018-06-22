package com.ai.controller.web.v1;


import com.ai.dao.VoiceDao;
import com.ai.domain.bo.Voice;
import com.ai.domain.vo.Message;
import com.ai.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("WebVoiceController")
@RequestMapping("/web/api/v1/voice")
public class VoiceController {


    @Autowired
    private VoiceDao voiceDao;

    @Autowired
    VoiceService voiceService;

    @PostMapping("/upload")
    public Message uploadVoice(@RequestParam("voice") MultipartFile voice) {

        if (voice.isEmpty()) {
            return new Message().error(3201, "文件不能为空，请确认voice不为空");
        }

        Voice v = voiceService.upload(voice);
        if(v!=null){
            return new Message().ok(3200,"上传成功").addData("voice",v).addData("voice",v);
        }
        return new Message().error(3201, "上传失败");
    }

    class V{
        private String text;
        private String pcm;

        public V(String text, String pcm) {
            this.text = text;
            this.pcm = pcm;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getPcm() {
            return pcm;
        }

        public void setPcm(String pcm) {
            this.pcm = pcm;
        }
    }
    @GetMapping("/test")
    public Object test(){
        List<Voice> voices =  voiceDao.select();

        Map<String,V> res = new HashMap<>();

        for(Voice voice:voices){
            res.put(voice.getHash(),new V("",voice.getPcm()));
        }
       return res;
    }
}
