package com.hnxx.zy.clms.security.sms;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Random;

/**
 * @program: test_security
 * @description: 验证码生成器
 * @author: nile
 * @create: 2020-05-15 13:43
 **/
@Component
public class SmsCodeGenerator {
    public static String getRandom(int num) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < num; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    public SmsCode  generate() {
        String code = getRandom(6);
        SmsCode smsCode =new SmsCode();
        smsCode.setCode(code);
        return smsCode;
    }
}
