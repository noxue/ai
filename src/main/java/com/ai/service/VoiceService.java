package com.ai.service;

import com.ai.domain.bo.Voice;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VoiceService {
    boolean addVoice(Voice voice);
    Voice findVoice(String hash);

    /**
     * 根据文件后缀判断是否允许上传
     * @param filename  文件名
     * @return  返回true表示
     */
    boolean allowUpload(String filename);
    Voice upload(MultipartFile voice);
}
