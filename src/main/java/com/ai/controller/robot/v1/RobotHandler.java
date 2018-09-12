package com.ai.controller.robot.v1;

import com.ai.controller.robot.v1.bean.Auth;
import com.ai.controller.robot.v1.bean.BaseAction;
import com.ai.controller.robot.v1.utils.Utils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

public class RobotHandler extends TextWebSocketHandler {

    public static Map<String,WebSocketSession> users = new HashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        System.out.println(message.getPayload());
        Auth auth =  Utils.Json2Bean(message.getPayload(), Auth.class);
        System.out.println(auth);
        TextMessage text = new TextMessage(auth.toString());
        session.sendMessage(text);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
