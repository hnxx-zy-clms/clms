/**
 * @FileName: Article
 * @Author: code-fusheng
 * @Date: 2020/3/21 21:39
 * Description: 文章实体类
 */
package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Article {

    /**
     * 文章id编号
     */
    private Integer articleId;

    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章作者
     */
    private String articleAuthor;

    /**
     * 文章图片
     */
    private String articleImage;

    /**
     * 文章描述
     */
    private String articleDesc;

    /**
     * 文章内容
     */
    private String articleContent;

    /**
     * 文章点赞量
     */
    private Integer articleGood;

    /**
     * 文章阅读量
     */
    private Integer articleRead;

    /**
     * 文章收藏量
     */
    private Integer articleCollection;

    /**
     * 文章分类
     */
    private Integer articleType;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 文章评论数
     */
    private Integer articleComment;

    /**
     * 文章来源
     */
    private String articleSource;

    /**
     * 创建时间
     */
    private String createdTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 是否启用（0否1是 默认1）
     */
    private Integer isEnabled;

    /**
     * 是否删除（0否1是 默认0）
     */
    private Integer isDeleted;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 文章评论列表
     */
    private List<Comment> commentList = new ArrayList<>();

}

