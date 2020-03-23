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
     * 用户名
     */
    private  String username;
    /**
     *密码
     */
    private  String password;
    /**
     *权限信息：ROLE_0 ROLE_1 ROLE_2
     */
    private String userRole;

}
