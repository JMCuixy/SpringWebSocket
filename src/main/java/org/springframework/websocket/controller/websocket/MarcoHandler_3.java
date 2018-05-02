package org.springframework.websocket.controller.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by XiuYin.Cui on 2018/5/1.
 * <p>
 * TextWebSocketHandler是AbstractWebSocketHandler的子类，它会拒绝处理二进制消息。
 * 它重载了handleBinaryMessage()方法，如果收到二进制消息的时候，将会关闭WebSocket连接.
 */
@Controller
public class MarcoHandler_3 extends TextWebSocketHandler {

    /**
     * 处理文本类信息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }
}
