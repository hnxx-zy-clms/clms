package com.hnxx.zy.clms.core.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 南街北巷
 * @data 2020/4/24 17:48
 */
@Data
public class GithubCount {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Date createdTime;
    private Date updateTime;
}
