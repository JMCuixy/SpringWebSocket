package org.springframework.websocket.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.websocket.model.Shout;

/**
 * Created by XiuYin.Cui on 2018/5/2.
 */
@Controller
public class StompController {


    private static final Logger LOGGER = LoggerFactory.getLogger(StompController.class);

    @MessageMapping("/marco")
    public void stompHandle(Shout shout){
        LOGGER.info("接收到消息：" + shout.getMessage());
    }


    /**
     * @SubscribeMapping 的主要应用场景是实现请求-回应模式。
     * 在请求-回应模式中，客户端订阅某一个目的地，然后预期在这个目的地上获得一个一次性的响应。
     * @return
     */
    @SubscribeMapping("/getShout")
    public Shout getShout(){
        Shout shout = new Shout();
        shout.setMessage("Hello STOMP");
        return shout;
    }
}
