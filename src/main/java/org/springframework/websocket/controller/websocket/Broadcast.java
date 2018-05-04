package org.springframework.websocket.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.websocket.model.Shout;

import java.security.Principal;

/**
 * Created by XiuYin.Cui on 2018/5/3.
 * <p>
 * 广播消息
 */
@Controller
public class Broadcast {

    private static final Logger LOGGER = LoggerFactory.getLogger(Broadcast.class);

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;


    /**
     * 广播消息，不指定用户，所有订阅此的用户都能收到消息
     * @param shout
     */
    @MessageMapping("/broadcastShout")
    public void broadcast(Shout shout) {
        simpMessageSendingOperations.convertAndSend("/topic/shouts", shout);
    }

    /**
     * 为特定用户指定目的地，最后消息会被发布在 /user/queue/shouts
     *
     * @param shout 消息对象
     * @param stompHeaderAccessor 用户认证信息
     */
    @MessageMapping("/singleShout")
    public void singleUser(Shout shout, StompHeaderAccessor stompHeaderAccessor) {
        String message = shout.getMessage();
        LOGGER.info("接收到消息：" + message);
        Principal user = stompHeaderAccessor.getUser();
        simpMessageSendingOperations.convertAndSendToUser(user.getName(), "/queue/shouts", shout);
    }

    /**
     * 为特定用户指定目的地，最后消息会被发布在  /user/queue/notifications-username
     *
     * @param principal 用户认证信息
     * @param shout 消息对象
     */
    @MessageMapping("/shout")
    @SendToUser("/queue/notifications")
    public Shout userStomp(Principal principal, Shout shout) {
        String name = principal.getName();
        String message = shout.getMessage();
        LOGGER.info("认证的名字是：{}，收到的消息是：{}", name, message);
        return shout;
    }


    /**
     * 消息异常处理
     * @param t
     * @return
     */
    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public Exception handleExceptions(Exception t){
        t.printStackTrace();
        return t;
    }



}
