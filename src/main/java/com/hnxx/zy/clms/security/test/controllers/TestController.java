package com.hnxx.zy.clms.security.test.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: clms
 * @description: 测试控制类
 * @author: nile
 * @create: 2020-03-18 12:12
 **/

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class TestController {

    @GetMapping("/login")
    public String showLogin() {
        return "login.html";
    }

}
