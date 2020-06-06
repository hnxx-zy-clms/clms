package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 南街北巷
 * @data 2020/6/3 3:49
 */
@Data
public class Role {
    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色职权
     */
    private String rolePosition;

    /**
     * 角色职称ID
     */
    private int rolePositionId;

    /**
     * 角色描述
     */
    private String roleDescription;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;

    /**
     * 启用
     */
    private String isEnabled;

    /**
     * 弃用
     */
    private String isDeleted;
}
