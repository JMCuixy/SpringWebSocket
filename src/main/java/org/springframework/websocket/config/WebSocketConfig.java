package org.springframework.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.websocket.controller.websocket.MarcoHandler_2;
import org.springframework.websocket.interceptor.WebSocketHandshakeInterceptor;

/**
 * Created by XiuYin.Cui on 2018/5/1.
 */

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    /**
     *
     * @param registry 该对象可以调用addHandler()来注册信息处理器。
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
          registry.addHandler(marcoHandler_2(),"/marco2")
                  .addInterceptors(webSocketHandshakeInterceptor()) //声明拦截器
                  .setAllowedOrigins("*"); //声明允许访问的主机列表

          //声明启用SockJS连接，如果前端还用 new WebSocket(url); 会报：Error during WebSocket handshake: Unexpected response code: 200
          registry.addHandler(marcoHandler_2(), "/marcoSockJS")
                  .setAllowedOrigins("*")
                  .withSockJS();
    }


    @Bean
    public MarcoHandler_2 marcoHandler_2(){
        return new MarcoHandler_2();
    }

    @Bean
    public WebSocketHandshakeInterceptor webSocketHandshakeInterceptor(){
        return new WebSocketHandshakeInterceptor();
    }
}
