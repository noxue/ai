package com.ai.service.impl;

import com.ai.config.UploadConfig;
import com.ai.dao.VoiceDao;
import com.ai.domain.bo.Voice;
import com.ai.service.VoiceService;
import com.ai.util.CommandUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class VoiceServiceImpl implements VoiceService {

    @Autowired
    VoiceDao voiceDao;

    @Autowired
    UploadConfig uploadConfig;

    @Override
    public boolean addVoice(Voice voice) {
        int ret = voiceDao.insert(voice);
        return ret == 1 ? true : false;
    }

    @Override
    public Voice findVoice(String hash) {
        return voiceDao.selectByPrimaryKey(hash);
    }

    @Override
    public Voice upload(MultipartFile voice) {

        String fileName = voice.getOriginalFilename();
        int size = (int) voice.getSize();


        try {
            String name = DigestUtils.sha1Hex(voice.getInputStream());

            // 如果已经存在，就不用再继续操作，直接返回即可
            Voice vv = findVoice(name);
            if (vv!=null) {
                return vv;
            }

            // 根据日期生成年月日目录结构
            String subDir = new SimpleDateFormat( "yyyy" + File.separatorChar + "MM" + File.separatorChar + "dd" + File.separatorChar).format(new Date());

            String path = uploadConfig.getPath();
            // wavPath保存相对路径
            String wavPath = subDir + name + voice.getOriginalFilename().substring(voice.getOriginalFilename().lastIndexOf("."));
            File dest = new File(path + wavPath);

            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdirs();
            }
            voice.transferTo(dest); //保存文件
            String pcmPath = subDir + name + ".pcm";

            // 这里需要绝对路径来处理，所以前面加上path
            boolean ok = new CommandUtil().wavToPcm(path + wavPath, path + pcmPath);

            if (!ok) {
                return null;
            }

            Voice voice1 = new Voice();
            //保存相对路径即可
            voice1.setPath(wavPath);
            voice1.setPcm(pcmPath);
            voice1.setHash(name);
            voice1.setFilename(voice.getOriginalFilename().substring(0,voice.getOriginalFilename().lastIndexOf(".")));
            voice1.setVisitedAt(new Date());
            voiceDao.insert(voice1);

            return voice1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean allowUpload(String filename) {

        int pos = filename.lastIndexOf(".");
        if (pos == -1) {
            return false;
        }

        return uploadConfig.getAllowList().contains(filename.substring(pos + 1));
    }
}
