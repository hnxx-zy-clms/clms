package com.hnxx.zy.clms.security.sms;

import com.hnxx.zy.clms.security.handles.CustomAuthenticationFailureHandler;
import com.hnxx.zy.clms.security.handles.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @program: test_security
 * @description: SmsCodeAuthenticationSecurityConfig
 * @author: nile
 * @create: 2020-05-15 14:42
 **/
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity builder) throws Exception {

        SmsValidateCodeFilter smsCodeAuthenticationFilter = new SmsValidateCodeFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));

        //配置smsCodeAuthenticationFilter成功和失败的处理器
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        //设置SmsCodeAuthenticationProvider的UserDetailsService
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);

        //把smsCodeAuthenticationFilter过滤器添加在UsernamePasswordAuthenticationFilter之前
        builder.authenticationProvider(smsCodeAuthenticationProvider);
        builder.addFilterBefore(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}