package com.hnxx.zy.clms.common.config;

import com.hnxx.zy.clms.core.entity.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 黑鲨
 * @date 2020/3/25 17:26
 * 监听用户的上线  下线
 */
@Configurable
public class WebSocketEventListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    public static Set<String> usernameSet= new HashSet<String>();

    @Resource
    private SimpMessageSendingOperations messagingTemplate;

    /***
     *  用户上线监听
     * @param event
     */
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            usernameSet.remove(username);
        }
   }

    /***
     * 用户离线监听
     * @param event
     */
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null&&!"".equals(username)){
            logger.info("User Disconnected:"+username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(ChatMessage.MessageType.LEAVE);
            chatMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public",chatMessage);
            usernameSet.remove(username);

            System.out.println("当前在线人数"+  usernameSet.size() +usernameSet.toString());
        }
    }
}
