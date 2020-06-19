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
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/getUserInfo")
    public Result<Object> authenticateUser(){
        User user = userMapper.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setUserPassword(null);
        Map<String,Object> map = new ConcurrentHashMap<>();
        map.put("user",user);
        map.put("menus",menuService.getMenuByRole(user.getUserPositionId()));
        return new Result<>(map);
    }

    @GetMapping("/getUserById/{id}")
    public Result<User> getUserById(@PathVariable Integer id){
        User user=userMapper.selectById(id);
        user.setUserPassword(null);
        return new Result<>(user);
    }
    @PutMapping("/updateUserIconById")
    public Result<Object> updateUserIconById(@RequestBody User user){
        userMapper.updateUserIconById(user.getUserId(),user.getUserIcon());
        return new Result<>("更新成功");
    }

    @GetMapping("/code/sms")
    public  Result<Object> createSmsCode(@RequestParam String mobile, HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(redisUtil.get(ip) == null){
            redisUtil.set(ip,1,58);
            SmsCode smsCode = smsCodeGenerator.generate();
            if(redisUtil.set(mobile, smsCode, ExpireTime)){
                if ("re".equals(mobile.substring(0,2))){
                    SendSms.send(mobile.substring(2),smsCode.getCode(),"SMS_190266443");
                }else{
                    SendSms.send(mobile,smsCode.getCode(),"SMS_190266443");
                }
            }else{
                return new Result<>(401,"验证码未发送，请稍后再试！");
            }
        } else {
            redisUtil.set(ip,(Integer)redisUtil.get(ip)+1,58);
            if ( (Integer)redisUtil.get(ip) > 10 ){
                redisUtil.set("rf"+ip,1,60*60*24);
            }
            return new Result<>(401,"请勿频繁操作");
        }
        return new Result<>();
    }
}
