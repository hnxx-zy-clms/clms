package com.hnxx.zy.clms.dto;

import lombok.Data;

/**
 * @author 南街北巷
 * @data 2020/4/10 18:57
 */
@Data
public class AccessTokenDTO {
    /**
     * 账号ID
     */
    private String client_id;

    /**
     * 密码
     */
    private String client_secret;

    /**
     * 随机码
     */
    private String code;

    /**
     * 回调地址
     */
    private String redirect_uri;

    /**
     * 状态码
     */
    private String state;
}
