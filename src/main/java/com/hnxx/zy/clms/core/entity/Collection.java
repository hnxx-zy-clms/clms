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
     * 文章标题
     */
    private String articleTitle;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 收藏时间
     */
    private String collectionTime;

    /**
     * 是否启用
     */
    private Integer isDeleted;
}
