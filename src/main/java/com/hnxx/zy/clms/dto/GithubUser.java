package com.hnxx.zy.clms.dto;

import lombok.Data;

/**
 * @author 南街北巷
 * @data 2020/4/10 18:57
 */
@Data
public class GithubUser {

    /**
     * github用户姓名
     */
    private String name;

    /**
     * github用户id
     */
    private String id;

    /**
     * github用户个性签名
     */
    private String bio;

    /**
     * github头像
     */
    private String avatarUrl;
}
