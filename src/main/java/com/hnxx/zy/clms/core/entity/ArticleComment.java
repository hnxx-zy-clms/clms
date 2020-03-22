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

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleComment implements Serializable {

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
    private Integer commentUser;

    /**
     * 评论文章id
     */
    private Integer commentArticle;

    /**
     * 评论点赞数
     */
    private Integer commentGood;

    /**
     * 评论时间
     */
    private String commentTime;

    /**
     * 是否启用（0否1是 默认1）
     */
    private Integer isEnabled;

    /**
     * 是否删除（0否1是 默认0）
     */
    private Integer isDeleted;

}
