package com.ai.controller.robot.v1;

import com.ai.controller.robot.v1.bean.Auth;
import com.ai.controller.robot.v1.bean.BaseAction;
import com.ai.controller.robot.v1.utils.Utils;
import com.ai.domain.bo.Gateway;
import com.ai.domain.bo.Sim;
import com.ai.service.GatewayService;
import com.ai.service.SimService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class RobotHandler extends TextWebSocketHandler {

    @Autowired
    private BeanUtil beanUtil;

    @Autowired
    private WebSocketServeice webSocketServeice;

    @Autowired
    private SimService simService;

    @Autowired
    private GatewayService gatewayService;

    public String exec(WebSocketSession session, String methodName, String content) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.ai.controller.robot.v1.WebSocketServeice");
            Method method = ReflectionUtils.findMethod(clazz, "on_" + methodName, BeanUtil.class, WebSocketSession.class, String.class);
            return (String) method.invoke(clazz.newInstance(), beanUtil, session, content);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        WebSocketServeice.putMsg(session, "{\"action\":\"NotAuth\"}\r\n\r\n");

        class MyThread extends Thread {

            private int i = 0;

            @Override
            public void run() {
                while (true) {
                    WebSocketMsg msg = WebSocketServeice.getMsg();
                    if (msg == null || msg.getMsg().equals("")) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    try {
                        msg.getSession().sendMessage(new TextMessage(msg.getMsg()));
                        WebSocketServeice.deleteMsg(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        Thread myThread1 = new MyThread();
        myThread1.start();

    }

    private String getAppid(WebSocketSession session) {
        return webSocketServeice.getUsers().get(session);
    }

    private boolean checkAuth(WebSocketSession session) {
        return this.getAppid(session) != null;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        super.handleTextMessage(session, message);
        String msg = message.getPayload();

        System.out.println(webSocketServeice.getUsers().get(session) + msg);

        BaseAction action = Utils.Json2Bean(msg, BaseAction.class);
        if (!action.getAction().equals("auth") && !this.checkAuth(session)) {
            WebSocketServeice.putMsg(session, "{\"action\":\"NotAuth\"}\r\n\r\n");
            return;
        }
        String str = this.exec(session, action.getAction(), msg);
        if (str != null && !"".equals(msg)) {
            WebSocketServeice.putMsg(session, str + "\r\n\r\n");
        }

        if (action.getAction().equals("auth") && this.checkAuth(session)) {
            // auth success
            String appid = webSocketServeice.getUsers().get(session);
            authSuccess(appid);

        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        webSocketServeice.deleteUser(session);
    }

    public void authSuccess(String appid) {
        webSocketServeice.workTime(appid, "admin");
        webSocketServeice.gateways(appid);
        webSocketServeice.tpl(appid, 2);

        PageInfo<Gateway> gatewayPageInfo = gatewayService.findGatewaysByAppId(1, 1000, appid);
        for (Gateway gateway : gatewayPageInfo.getList()) {
            PageInfo<Sim> sims = simService.findSimByGatewayId(1, 10000, gateway.getId().intValue());
            for (Sim sim : sims.getList()) {
                webSocketServeice.sim(appid, sim.getId());
            }
        }
    }
}
