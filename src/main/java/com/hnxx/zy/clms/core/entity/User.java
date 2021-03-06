package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 南街北巷
 * @data 2020/5/9 8:54
 */
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 账号
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    /**
     * 姓名
     */
    private String name;

    /**
     * 学院id
     */
    private String userCollegeId;

    /**
     * 班级id
     */
    private String userClassesId;

    /**
     * 用户组id
     */
    private Integer userGroupId;

    /**
     * 头像
     */
    private String userIcon;

    /**
     * 描述
     */
    private String userDescription;

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
     * 用户职称id
     */
    private int userPositionId;

    /**
     * 是否启用(1为启用，0为弃用，默认为1)
     */
    private int isEnabled;
    ;

    /**
     * 是否已删除(1为已删除，0为未删除，默认为0)
     */
    private int isDeleted;
}
