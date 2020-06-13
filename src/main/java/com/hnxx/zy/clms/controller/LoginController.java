package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.RedisUtil;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.service.UserService;
import com.hnxx.zy.clms.security.sms.SmsCode;
import com.hnxx.zy.clms.security.sms.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;

/**
 * @author 南街北巷
 * @data 2020/4/11 14:57
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ResponseBody
    public Result<String> register(@RequestBody User user){
        System.out.println(user.toString());
        Integer state = userService.addUser(user);
        if (state == 0){
            return new Result<>(401,StateEnum.USERNAME_EXIT.getMsg());
        }else if(state == 1){
            return new Result<>(401,StateEnum.USER_MOBILE_EXIT.getMsg());
        }else {
            return new Result<>("注册成功");
        }
    }


    /**
     * chat专属
     * @return  黑鲨-code
     */
    @PostMapping("/isLogin")
    @ResponseBody
    public String isLogin(){
        return "ok";
    }

}
