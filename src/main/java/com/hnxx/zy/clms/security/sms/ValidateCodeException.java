package com.hnxx.zy.clms.security.sms;

import org.springframework.security.core.AuthenticationException;

/**
 * @program: test_security
 * @description: ValidateCodeException
 * @author: nile
 * @create: 2020-05-15 16:40
 **/
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}