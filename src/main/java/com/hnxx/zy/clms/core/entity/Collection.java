/**
 * @FileName: Collection
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:10
 * Description: 收藏实体类
 */
package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Collection implements Serializable {

    private static final long serialVersionUID = 4593901166353847852L;

    /**
     * 收藏id
     */
    private Integer collectionId;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 文章标题 字段用于后台系统管理
     */
    private String articleTitle;

    /**
     * 提问id
     */
    private Integer questionId;

    /**
     * 问题描述 字段用于后台系统管理
     */
    private String questionDescription;

    /**
     * 视频id
     */
    private Integer videoId;

    /**
     * 视频标题 字段用于后台系统管理
     */
    private String title;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 收藏类型
     * 1: 文章收藏
     * 2: 提问收藏
     * 3: 视频收藏
     */
    private Integer collectionType;

    /**
     * 收藏时间
     */
    private String collectionTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;
}
