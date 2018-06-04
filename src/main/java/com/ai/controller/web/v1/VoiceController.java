package com.ai.controller.web.v1;


import com.ai.domain.vo.Message;
import com.ai.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController("WebVoiceController")
@RequestMapping("/web/api/v1/voice")
public class VoiceController {


    @Autowired
    VoiceService voiceService;

    @PostMapping("/upload")
    public Message uploadVoice(@RequestParam("voice") MultipartFile voice) {

        if(voice.isEmpty()) {
            return new Message().error(3201,"文件不能为空，请确认voice不为空");
        }

        try {
            voiceService.upload(voice);
            return new Message().ok(3200,"上传成功");
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return new Message().error(3202,"上传失败");
        } catch (IOException e) {
            e.printStackTrace();
            return new Message().error(3203,"上传失败");
        } catch (MultipartException e){
            return new Message().error(3204,"数据类型不正确");
        }
    }
}
