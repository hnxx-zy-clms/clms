package com.hnxx.zy.clms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 南街北巷
 * @data 2020/4/11 14:57
 */
@Controller
public class LoginController {
    /**
     *
     * 登录与注册路径导航
     */
    @GetMapping("/")
    public String login(){
        return "index";
    }
    @GetMapping("/login")
    public String callbackLogin(){
        return "index";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    /**
     * 主页导航
     */
    @GetMapping("/home")
    public String mainPage(){
        return "home";
    }
}
