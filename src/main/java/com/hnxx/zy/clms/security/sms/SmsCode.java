package com.hnxx.zy.clms.security.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @program: test_security
 * @description: 短信验证码
 * @author: nile
 * @create: 2020-05-11 22:37
 **/
@Data
public class SmsCode implements Serializable {

    private String code;

}
