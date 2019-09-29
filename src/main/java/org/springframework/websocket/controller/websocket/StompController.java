package org.springframework.websocket.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.websocket.model.Shout;

/**
 * Created by XiuYin.Cui on 2018/5/2.
 */
@Controller
public class StompController {


    private static final Logger LOGGER = LoggerFactory.getLogger(StompController.class);

    /**
     * 默认情况下，帧所发往的目的地会与触发处理器方法的目的地相同，只不过会添加上“/topic”前缀。
     *
     * @param shout
     */
    @MessageMapping("/marco")
    @SendTo("/topic/marco") //可重写目的地，@MessageMapping 会发送到消息代理，客户端再从消息代理订阅
    public Shout stompHandle(Shout shout) {
        LOGGER.info("接收到消息：" + shout.getMessage());
        Shout s = new Shout();
        s.setMessage("Polo!");
        return s;
    }


    /**
     * @return
     * @SubscribeMapping 的主要应用场景是实现请求-回应模式。
     * 在请求-回应模式中，客户端订阅某一个目的地，然后预期在这个目的地上获得一个一次性的响应。
     */
    @SubscribeMapping("/getShout")
    //@SendTo("/app/marco") // @SubscribeMapping 默认直接返回给客户端,如果你加了SendTo的话则要经过代理
    public Shout getShout() {
        Shout shout = new Shout();
        shout.setMessage("Hello STOMP");
        return shout;
    }
}
