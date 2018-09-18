package com.ai.controller.robot.v1;

import com.ai.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BeanUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private AppService appService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserConfigService userConfigService;

    @Autowired
    private WebSocketServeice webSocketServeice;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private GatewayService gatewayService;

    @Autowired
    private SimService simService;

    public SimService getSimService() {
        return simService;
    }

    public void setSimService(SimService simService) {
        this.simService = simService;
    }

    public GatewayService getGatewayService() {
        return gatewayService;
    }

    public void setGatewayService(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    public TemplateService getTemplateService() {
        return templateService;
    }

    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }

    public UserConfigService getUserConfigService() {
        return userConfigService;
    }

    public void setUserConfigService(UserConfigService userConfigService) {
        this.userConfigService = userConfigService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public AppService getAppService() {
        return appService;
    }

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    public WebSocketServeice getWebSocketServeice() {
        return webSocketServeice;
    }

    public void setWebSocketServeice(WebSocketServeice webSocketServeice) {
        this.webSocketServeice = webSocketServeice;
    }
}
