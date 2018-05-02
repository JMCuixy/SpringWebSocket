package org.springframework.websocket.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by XiuYin.Cui on 2018/5/1.
 */
@Controller
public class MarcoHandler_1 implements WebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarcoHandler_1.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("WebSocket 连接建立......");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        LOGGER.info("处理 WebSocketMessage 消息......");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
       exception.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        LOGGER.info("WebSocket 连接关闭......");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
