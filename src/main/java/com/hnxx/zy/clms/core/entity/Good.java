/**
 * @FileName: Good
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:27
 * Description: 点赞实体类
 */
package com.hnxx.zy.clms.core.entity;

import lombok.Data;

@Data
public class Good {

    /**
     * 点赞id
     */
    private Integer goodId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 文章id
     */
    private Integer articleId;

    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 点赞时间
     */
    private String goodTime;

}
