package com.hnxx.zy.clms.security.test.controllers;

import com.hnxx.zy.clms.common.utils.RedisUtil;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.MenuService;
import com.hnxx.zy.clms.security.sms.SendSms;
import com.hnxx.zy.clms.security.sms.SmsCode;
import com.hnxx.zy.clms.security.sms.SmsCodeGenerator;
import com.hnxx.zy.clms.security.sms.ValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @program: clms
 * @description: 测试控制类
 * @author: nile
 * @create: 2020-03-18 12:12
 **/

@RestController
public class TestController {

    /**
     *redis中存储的过期时间180s
     */
    private static int ExpireTime = 180;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private MenuService menuService;

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private SendSms sendSms;

    @GetMapping("/getUserInfo")
    public Result<Object> authenticateUser(){
        User user = userMapper.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setUserPassword(null);
        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("user",user);
        map.put("menus",menuService.getMenuByRole(user.getUserPositionId()));
        return new Result<>(map);
    }

    @PutMapping("/updateUserIconById")
    public Result<Object> updateUserIconById(@RequestBody User user){
        userMapper.updateUserIconById(user.getUserId(),user.getUserIcon());
        return new Result<>("更新成功");
    }

    @GetMapping("/code/sms")
    public  Result<Object> createSmsCode(@RequestParam String mobile){
        SmsCode smsCode = smsCodeGenerator.generate();
        if(redisUtil.set(mobile, smsCode, ExpireTime)){
            SendSms.send(mobile,smsCode.getCode(),"SMS_190266443");
        }else{
            throw new ValidateCodeException("验证码写入缓存错误");
        }
        return new Result<>();
    }
}
