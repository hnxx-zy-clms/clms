package com.hnxx.zy.clms.security.test.entity;

import lombok.Data;

/**
 * @program: clms
 * @description: security测试用户bean
 * @author: nile
 * @create: 2020-03-22 22:11
 **/
@Data
public class SysUser {

    /**
     * 用户ID
     */
    private  Integer userId;

    /**
     * 用户名
     */
    private  String userName;
    /**
     *密码
     */
    private  String userPassword;
    /**
     *权限信息：0 1 2
     */
    private Integer userPositionId;

}
