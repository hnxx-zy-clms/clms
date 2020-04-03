package com.hnxx.zy.clms.core.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 黑鲨
 * @date 2020/3/25 17:23
 */
@Data
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = -8724518310083269150L;
    /**
     * 消息类型
     */
    private MessageType type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息发送者
     */
    private String sender;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 三种状态
     */
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
