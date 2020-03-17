/**
 * @FileName: Xxx
 * @Author: fusheng
 * @Date: 2020/3/17 13:20
 * Description: 模版实体类
 */
package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 要求 ： 统一使用 Lombok 插件
 * @author fusheng
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Xxx implements Serializable {

    /**
     * 1. File -> setting -> plugins  安装 GenerateSerialVersionUID 插件
     * 2. 光标 Serializable Ait+Insert 生成序列化唯一标识
     */
    private static final long serialVersionUID = 2347422088362372999L;

    /**
     * 1. 实体类主键统一使用 Integer 包装类
     * 2. 使用包装类的好处，当我们不想给这个属性设置值的时候，我们可以给一个null值（前提是数据库字段为空）
     */

    /**
     * xx id
     */
    private Integer xxId;
    /**
     * xx 名字
     */
    private String xxName;
    /**
     * 创建时间
     */
    private String createdTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 乐观锁
     */
    private Integer version;
    /**
     * 是否启用，0否1是 默认1
     */
    private Integer isEnabled;

    /**
     * 是否删除，0否1是 默认0
     */
    private Integer isDeleted;
}
