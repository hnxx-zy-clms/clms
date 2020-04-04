package com.hnxx.zy.clms.controller;


import com.hnxx.zy.clms.common.config.WebSocketEventListener;
import com.hnxx.zy.clms.common.utils.CurrentUserNameUtil;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.ChatMessage;
import com.hnxx.zy.clms.core.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;


/**
 * @author 黑鲨
 * @date 2020/3/25 16:27
 */

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Resource
    private ChatService chatService;
    /**
     * 群发给所有在聊天室的用户   并将信息存储置数据库用于消息记录
     *
     * @param chatMessage
     * @return
     */
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatService.chatSave(chatMessage);

        return chatMessage;
    }

    /**
     * 群发通知所有人  新进入聊天室 的用户  并将信息存储置数据库用于消息记录
     *
     * @param chatMessage
     * @param headerAccessor
     * @return
     */
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        chatService.chatSave(chatMessage);
        return chatMessage;
    }

    /**
     * 获取聊天记录
     * type   为1   代表查询一个小时以前的聊天信息
     *        为2  代表查询2个小时以前的聊天信息
     *        为3 代表查询当天的聊天信息
     * @return
     * @GetMapping("/chatMessage/{date}") public Result<Object>  chatMessage(@PathVariable("date") Date date){
     */
    @RequestMapping("/chatMessage")
    public   @ResponseBody Result<Object> chatMessage(@RequestParam("type")Integer type ) {

        return new Result<>(chatService.allChat(type));
    }



    /**
     * 进入聊天界面进行聊天  并且将当前登录的用户名存入集合
     * @param model
     * @return
     */
    @RequestMapping("/toChat")
    public String toChat( Model model){
        String currentUserName = new CurrentUserNameUtil().getCurrentUserName();
        if(currentUserName != null&&!"".equals(currentUserName) ){
            model.addAttribute("username",currentUserName);
            WebSocketEventListener.usernameSet.add(currentUserName);
        }
        return  "chatPage";
    }


    /***
     * 获取进入聊天室的用户名
     */
    @RequestMapping("currentUsername")
    public @ResponseBody Set<String>  currentUsernaem(){
        return WebSocketEventListener.usernameSet;
    }



}
