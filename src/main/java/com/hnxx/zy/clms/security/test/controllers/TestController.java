package com.hnxx.zy.clms.security.test.controllers;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


/**
 * @program: clms
 * @description: 测试控制类
 * @author: nile
 * @create: 2020-03-18 12:12
 **/

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/getUserInfo")
    public Result<Object> authenticateUser(){
        SysUser user = userMapper.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setUserPassword(null);
        return new Result<>(user);
    }


}
