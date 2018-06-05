package com.ai.service;

import com.ai.domain.bo.Voice;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VoiceService {
    boolean addVoice(Voice voice);
    Voice findVoice(Long id);
    boolean upload(MultipartFile voice) throws IOException,IllegalStateException;
}
