/**
 * @FileName: Type
 * @Author: code-fusheng
 * @Date: 2020/3/22 12:52
 * Description: 文章类型实体
 */
package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleType implements Serializable {

    private static final long serialVersionUID = 45631090719388925L;

    /**
     * 分类id
     */
    private Integer typeId;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 文章数
     */
    private Integer typeCount;

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
}
