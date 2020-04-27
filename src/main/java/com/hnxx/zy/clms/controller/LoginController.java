package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

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
    @ResponseBody
    public ResponseEntity<Object> callbackLogin(){
        return new ResponseEntity<>(new Result<>(ResultEnum.NOT_LOGIN), HttpStatus.FORBIDDEN);
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
