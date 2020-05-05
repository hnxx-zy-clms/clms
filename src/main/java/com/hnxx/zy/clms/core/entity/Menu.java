package com.hnxx.zy.clms.core.entity;

import lombok.Data;

/**
 * @program: clms
 * @description: 前端用户菜单类
 * @author: nile
 * @create: 2020-05-05 15:53
 **/
@Data
public class Menu {

    /**
     * 路由地址
     */
    private String path;

    /**
     * 路由名称，建议唯一
     */
    private String name;

    /**
     * 该路由对应页面的 组件
     */
    private String component;

}
