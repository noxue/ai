package com.ai.controller.robot.v1;

import org.springframework.web.socket.WebSocketSession;

public class WebSocketMsg {
    private WebSocketSession session;
    private String msg;

    public WebSocketMsg(WebSocketSession session, String msg) {
        this.session = session;
        this.msg = msg;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}