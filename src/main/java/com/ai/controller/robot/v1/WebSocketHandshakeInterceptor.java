package com.ai.controller.robot.v1;

import com.ai.domain.bo.App;
import com.google.gson.Gson;
import org.apache.tomcat.websocket.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {


    private static final Logger logger = LoggerFactory.getLogger(WebSocketHandshakeInterceptor.class);

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (!(serverHttpRequest instanceof ServletServerHttpRequest)) {
            return false;
        }

        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
        HttpSession httpSession = servletRequest.getServletRequest().getSession(true);
        if (null == httpSession) {
            return false;
        }

        HttpServletRequest reqest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        String appid = reqest.getParameter("appid");
        String key = reqest.getParameter("key");
        map.put("appid", appid);
        map.put("key", key);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest,
                               ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}