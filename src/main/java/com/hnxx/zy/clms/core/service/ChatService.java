package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.ChatMessage;

import java.util.List;

/**
 * @author 黑鲨
 * @date 2020/3/25 17:51
 */
public interface ChatService {

    void  chatSave(ChatMessage chatMessage);

    List<ChatMessage> allChat(Integer type);
}
