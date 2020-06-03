/**
 * @FileName: Comment
 * @Author: code-fusheng
 * @Date: 2020/3/22 10:55
 * Description: 评论实体表
 */
package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment implements Serializable {

    private static final long serialVersionUID = -7292491729846673669L;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论人
     */
    private String commentUser;

    /**
     * 评论文章id
     */
    private Integer commentArticle;

    /**
     * 评论文章标题
     */
    private String articleTitle;

    /**
     * 评论评论量
     */
    private Integer commentCount;

    /**
     * 评论点赞数
     */
    private Integer commentGood;

    /**
     * 评论时间
     */
    private String commentTime;

    /**
     * 评论类型
     * 0:直接评论   ？（评论） @ ？（文章）
     * 1:评论的评论 带有指定的父级评论对象 存在回复的用户 ？（评论） @ ？（评论）
     */
    private Integer commentType;

    /**
     * 是否启用（0否1是 默认1）
     */
    private Integer isEnabled;

    /**
     * 是否删除（0否1是 默认0）
     */
    private Integer isDeleted;

    /**
     * 父级id
     */
    private Integer pid;

    /**
     * 评论列表
     */
    private List<Comment> commentList = new ArrayList<>();

    /**
     * 作者头像 vo属性
     */
    private String userIcon;

    /**
     * 对应父级的评论的 用户名
     */
    private String parentCommentUser;

    /**
     * 评论点赞标识 仅用于前端展示，不做数据持久化
     */
    private boolean goodCommentFlag = false;
}
