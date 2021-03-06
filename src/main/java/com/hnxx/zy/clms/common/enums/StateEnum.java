/**
 * @FileName: StateEnum
 * @Author: code-fusheng
 * @Date: 2020/3/17 20:44
 * Description: 状态码枚举
 */
package com.hnxx.zy.clms.common.enums;

import lombok.Getter;

@Getter
public enum StateEnum {
    /**
     * 逻辑删除状态
     */
    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除"),

    /**
     * 启用状态
     */
    ENABLED(1, "启用"),
    NOT_ENABLE(0, "未启用"),

    /**
     * 性别状态
     */
    SEX_MAN(1, "男"),
    SEX_WOMAN(2, "女"),

    /**
     * 请求访问状态枚举
     */
    REQUEST_SUCCESS(1, "请求正常"),
    REQUEST_ERROR(0, "请求异常"),

    /**
     * 评论类型枚举
     */
    ARTICLE_COMMENT(0,"文章的评论"),
    COMMENT_COMMENT(1,"评论的评论"),
    QUESTION_COMMENT(2,"问题的评论"),
    ANSWER_COMMENT(3,"答疑的评论"),

    /**
     * 点赞类型枚举
     */
    ARTICLE_GOOD(0, "文章的点赞"),
    COMMENT_GOOD(1, "评论的点赞"),
    QUESTION_GOOD(2, "问题的点赞"),
    ANSWER_GOOD(3, "答复的点赞"),
    VIDEO_GOOD(4, "视屏的点赞"),

    /**
     * 收藏类型枚举
     */
    ARTICLE_COLLECTION(1, "文章收藏"),
    QUESTION_COLLECTION(2, "提问收藏"),
    VIDEO_COLLECTION(3, "视频收藏"),

    /**
     * 问题状态枚举
     */
    NO_SOLVE_QUESTION(0, "未解决"),
    IS_SOLVE_QUESTION(1, "已解决"),

    /**
     * 答复类型标记
     */
    NO_ADOPT_ANSWER(0, "未采纳"),
    IS_ADOPT_ANSWER(1, "已采纳"),

    /**
     * 用户注册响应
     */
    USERNAME_EXIT(0,"用户名已存在"),
    USER_MOBILE_EXIT(0,"电话号码已被注册"),

    /**
     * 消息状态类型枚举
     */
    MESSAGE_IS_READ(1, "消息已读"),
    MESSAGE_NO_READ(0, "消息未读"),

    /**
     * 消息类型枚举
     */
    SYSTEM_MESSAGE(0, "系统消息"),
    ARTICLE_COMMENT_MESSAGE(1, "文章评论消息"),
    REPLAY_COMMENT_MESSAGE(2, "评论回复消息"),
    ARTICLE_GOOD_MESSAGE(3, "文章点赞消息"),
    COMMENT_GOOD_MESSAGE(4, "评论点赞消息"),
    QUESTION_GOOD_MESSAGE(5, "提问点赞消息"),
    ANSWER_GOOD_MESSAGE(6, "答复点赞消息"),
    VIDEO_GOOD_MESSAGE(7, "视频点赞消息"),
    ARTICLE_COLLECTION_MESSAGE(8, "文章收藏消息"),
    QUESTION_COLLECTION_MESSAGE(9, "提问收藏消息"),
    ANSWER_COLLECTION_MESSAGE(10, "答复收藏消息"),
    VIDEO_COLLECTION_MESSAGE(11, "视频收藏消息"),
    READ_MESSAGE(12, "批阅消息"),
    ANSWER_MESSAGE(13, "答复消息"),
    REMIND_MESSAGE(14, "提醒消息"),
    PRIVATE_MESSAGE(15, "私信消息"),
    OTHER_MESSAGE(16, "其他消息")

    ;

    /**
     * code 状态码
     * msg 状态信息
     */
    private Integer code;
    private String msg;

    StateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
