package com.ai.service.impl;

import com.ai.dao.VoiceDao;
import com.ai.domain.bo.Voice;
import com.ai.service.VoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

        // 这里只是简单例子，文件直接输出到项目路径下。
        // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
        // 还有关于文件格式限制、文件大小限制，详见：中配置。
        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(new File(voice.getOriginalFilename())));
        out.write(voice.getBytes());
        out.flush();
        out.close();

//        String fileName = voice.getOriginalFilename();
//        int size = (int) voice.getSize();
//        System.out.println(fileName + "-->" + size);
//
//        String path = "static/upload" ;
//        File dest = new File(path + "/" + fileName);
//        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
//            dest.getParentFile().mkdir();
//        }
//
//        voice.transferTo(dest); //保存文件
        return true;
    }
}
