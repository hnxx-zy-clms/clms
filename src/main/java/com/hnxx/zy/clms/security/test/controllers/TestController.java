package com.hnxx.zy.clms.security.test.controllers;

import com.hnxx.zy.clms.security.jwt.JwtProvider;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @program: clms
 * @description: 测试控制类
 * @author: nile
 * @create: 2020-03-18 12:12
 **/

@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SysUser loginRequest) {
        SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok( loginRequest.getUserName());
    }
    @GetMapping("/ttttt")
    @ResponseBody
    public String User() {
        logger.info("当前登录用户名: "+SecurityContextHolder.getContext().getAuthentication().getName());
        return "s";
    }


}
