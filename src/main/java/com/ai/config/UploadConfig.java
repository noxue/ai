package com.ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Configuration
public class UploadConfig {
    @Value("${upload.path}")
    private String path;

    @Value("${upload.allow}")
    private String allow;

    @Value("${upload.ffmpeg}")
    private String ffmpeg;

    public List<String> getAllowList(){
        return Arrays.asList(allow.split("|"));
    }

    public String getAllow() {
        return allow;
    }

    public void setAllow(String allow) {
        this.allow = allow;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;
        factory.setMaxFileSize("10MB"); // KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("100MB");
        // Sets the directory location where files will be stored.
        String location = System.getProperty("user.dir") + "/data/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        factory.setLocation(location);
        return factory.createMultipartConfig();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFfmpeg() {
        return ffmpeg;
    }

    public void setFfmpeg(String ffmpeg) {
        this.ffmpeg = ffmpeg;
    }
}
