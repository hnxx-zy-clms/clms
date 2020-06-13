package com.hnxx.zy.clms.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @FileName: Message
 * @Author: code-fusheng
 * @Date: 2020/6/13 10:27
 * @version: 1.0
 * Description: 消息实体类
 */

@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 5232581371124009099L;

    /**
     * 消息id
     */
    private Integer messageId;

    /**
     * 消息内容 内容id
     */
    private Integer messageContent;

    /**
     * 消息描述
     */
    private String messageDesc;

    /**
     * 消息发送者id
     */
    private String sendUser;

    /**
     * 消息接收者id
     */
    private String receiveUser;

    /**
     * 消息类型
     * 0:系统消息 1:文章评论消息 2:评论回复消息
     * 3:文章点赞消息 4:评论点赞消息 5:提问点赞消息 6:答复点赞消息 7:视频点赞消息
     * 8:文章收藏消息 9:提问收藏消息 10:答复收藏消息 11:视频收藏消息
     * 12:批阅消息 13:答复通知 14:提醒消息（用于提问指定对象） 15:私信（） 16: 其他消息
     */
    private Integer messageType;

    /**
     * 消息状态 0:未读 1:已读
     */
    private Integer messageState;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新时间
     */
    private String updateTime;
}
