package com.ai.controller.robot.v1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(myHandler(),"/robot").addInterceptors(new WebSocketHandshakeInterceptor())
                .setAllowedOrigins("http://127.0.0.1","http://192.168.3.190","http://localhost");
    }

    @Bean
    WebSocketHandler myHandler() {
        return new RobotHandler();
    }

}
