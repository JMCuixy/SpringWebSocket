package org.springframework.websocket.controller.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

/**
 * Created by XiuYin.Cui on 2018/5/1.
 * <p>
 * BinaryWebSocketHandler也是AbstractWeb-SocketHandler的子类，
 * 它重载了handleTextMessage()方法，如果接收到文本消息的话，将会关闭连接。
 */
@Controller
public class MarcoHandler_4 extends BinaryWebSocketHandler {


    /**
     * 处理二进制信息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
    }

}
