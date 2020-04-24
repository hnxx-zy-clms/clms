package com.hnxx.zy.clms.dto;

import lombok.Data;

/**
 * @author 南街北巷
 * @data 2020/4/10 18:57
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
