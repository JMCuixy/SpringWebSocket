package org.springframework.websocket.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

/**
 * Created by XiuYin.Cui on 2018/5/1.
 */
public class MarcoHandler_2 extends AbstractWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarcoHandler_2.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        LOGGER.info("WebSocket 连接建立......");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        LOGGER.info("接收到消息：" + message.getPayload());
        Thread.sleep(2000);
        //发送文本消息
        session.sendMessage(new TextMessage("Polo!"));

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus){
        LOGGER.info("WebSocket 连接关闭......");
    }

}
