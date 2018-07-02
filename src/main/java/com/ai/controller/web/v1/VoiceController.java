package com.ai.controller.web.v1;


import com.ai.config.UploadConfig;
import com.ai.dao.VoiceDao;
import com.ai.domain.bo.Voice;
import com.ai.domain.vo.Message;
import com.ai.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("WebVoiceController")
@RequestMapping("/web/api/v1/voice")
public class VoiceController {

    @Autowired
    VoiceService voiceService;

    @Autowired
    UploadConfig uploadConfig;

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

    @RequestMapping("/file/{type}/{hash}")
    public void file(@PathVariable  String type, @PathVariable  String hash, HttpServletResponse response){

        Voice voice = voiceService.findVoice(hash);
        if (voice== null) return;

        // 下载本地文件
        String fileName = uploadConfig.getPath()+ voice.getPath();

        if ("pcm".equalsIgnoreCase(type)) {
            fileName = uploadConfig.getPath()+ voice.getPcm();
        }

        // 读到流中
        InputStream inStream = null;// 文件的存放路径
        try {
            inStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + voice.getFilename() + "." + type + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
