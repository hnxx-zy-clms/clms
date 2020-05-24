package com.hnxx.zy.clms.core.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 南街北巷
 * @data 2020/4/24 17:48
 */
@Data
public class GithubCount {
    /**
     * github用户id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 账号id
     */
    private String accountId;
    /**
     * 账号钥匙
     */
    private String token;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
