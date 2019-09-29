package org.springframework.websocket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import java.security.Principal;

/**
 * Created by XiuYin.Cui on 2018/5/2.
 */

@Configuration
@EnableWebSocketMessageBroker
@PropertySource("classpath:resources.properties")
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.port}")
    private Integer port;

    @Value("${rabbitmq.userName}")
    private String userName;

    @Value("${rabbitmq.password}")
    private String password;

    /**
     * 将 "/stomp" 注册为一个 STOMP 端点。这个路径与之前发送和接收消息的目的地路径有所
     * 不同。这是一个端点，客户端在订阅或发布消息到目的地路径前，要连接到该端点。
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp").withSockJS();
    }


    /**
     * 如果不重载它的话，将会自动配置一个简单的内存消息代理，用它来处理以"/topic"为前缀的消息
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //基于内存的STOMP消息代理
        registry.enableSimpleBroker("/queue", "/topic");

        //基于RabbitMQ 的STOMP消息代理
/*        registry.enableStompBrokerRelay("/queue", "/topic")
                .setRelayHost(host)
                .setRelayPort(port)
                .setClientLogin(userName)
                .setClientPasscode(password);*/

        registry.setApplicationDestinationPrefixes("/app", "/foo");
        registry.setUserDestinationPrefix("/user");
    }


    /**
     * 1、设置拦截器
     * 2、首次连接的时候，获取其Header信息，利用Header里面的信息进行权限认证
     * 3、通过认证的用户，使用 accessor.setUser(user); 方法，将登陆信息绑定在该 StompHeaderAccessor 上，在Controller方法上可以获取 StompHeaderAccessor 的相关信息
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptorAdapter() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //1、判断是否首次连接
                if (StompCommand.CONNECT.equals(accessor.getCommand())){
                    //2、判断用户名和密码
                    String name = accessor.getNativeHeader("username").get(0);
                    String pas = accessor.getNativeHeader("password").get(0);

                    if ("admin".equals(name) && "admin".equals(pas)){
                        Principal principal = new Principal() {
                            @Override
                            public String getName() {
                                return name;
                            }
                        };
                        accessor.setUser(principal);
                        return message;
                    }else {
                        return null;
                    }
                }
                //不是首次连接，已经登陆成功
                return message;
            }

        });
    }
}
