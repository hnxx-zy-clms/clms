package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: clms
 * @description: 视频评论类
 * @author: nile
 * @create: 2020-05-31 13:03
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoComment implements Serializable {

    private static final long serialVersionUID = -7292491729846672669L;

    /**
     * 评论id
     */
    private Integer videoCommentId;

    /**
     * 评论内容
     */
    private String videoCommentContent;

    /**
     * 评论人
     */
    private String videoCommentUserId;

    /**
     * 评论人
     */
    private String userIcon;

    /**
     * 评论人
     */
    private String userName;

    /**
     * 视频id
     */
    private Integer videoId;

    /**
     * 评论评论量
     */
    private Integer videoCommentCount;

    /**
     * 评论点赞数
     */
    private Integer videoCommentGood;

    /**
     * 评论时间
     */
    private String videoCommentCreatedTime;

    /**
     * 评论父级Id
     */
    private Integer videoCommentParent;

    /**
     * 是否启用（默认0）
     */
    private Integer isEnabled;

    /**
     * 是否删除（0否1是 默认0）
     */
    private Integer isDeleted;

    /**
     * 回复人用户名
     */
    private String videoCommentParentName;

    /**
     * 回复评论数量
     */
    private Integer videoCommentParentSum;

    /**
     * 评论列表
     */
    private List<VideoComment> videoCommentList = new ArrayList<>();
}
