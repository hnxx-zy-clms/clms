package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.ChatMessage;
import com.hnxx.zy.clms.core.mapper.ChatMapper;
import com.hnxx.zy.clms.core.service.ChatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 黑鲨
 * @date 2020/3/25 17:51
 */
@Service
@Transactional
public class ChatServerImpl implements ChatService {

    @Resource
    private ChatMapper chatMapper;

    /**
     * 存储聊天信息
     *
     * @param chatMessage
     */
    @Override
    public void chatSave(ChatMessage chatMessage) {

        if (!StringUtils.isNullOrEmpty(chatMessage)) {
            chatMessage.setCreatedTime(new Date(System.currentTimeMillis()));
            chatMapper.chatSave(chatMessage);
        }
    }

    /**
     * 查询聊天记录
     *
     * @return
     */
    @Override
    public List<ChatMessage> allChat(Integer type) {
        if (type == null && "".equals(type)) {
            type = 1;
        }
        Date startTime;
        Date endTime;
        long currentTimeMillis = System.currentTimeMillis();
        endTime = new Date(currentTimeMillis);
        if (type == 1) {
            startTime = new Date(currentTimeMillis - 60 * 60 * 1000);
            return chatMapper.allChat(startTime, endTime);
        } else if (type == 2) {

            startTime = new Date(currentTimeMillis - 2*60 * 60 * 1000);
            return chatMapper.allChat(startTime, endTime);
        } else if (type == 3) {
            startTime = new Date(currentTimeMillis - 24*60 * 60 * 1000);
            return chatMapper.allChat(startTime, endTime);
        }
        return null;

    }
}
