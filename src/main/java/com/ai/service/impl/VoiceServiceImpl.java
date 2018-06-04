package com.ai.service.impl;

import com.ai.dao.VoiceDao;
import com.ai.domain.bo.Voice;
import com.ai.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Configuration
@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    VoiceDao voiceDao;


    @Override
    public boolean addVoice(Voice voice) {
        int ret = voiceDao.insert(voice);
        return ret == 1 ? true : false;
    }

    @Override
    public Voice findVoice(Long id) {
        return voiceDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean upload(MultipartFile voice) throws IOException,IllegalStateException {

        String fileName = voice.getOriginalFilename();
        int size = (int) voice.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "e:/test" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }

        voice.transferTo(dest); //保存文件
        return true;
    }
}
