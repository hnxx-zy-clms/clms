package com.hnxx.zy.clms.security.test.controllers;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuService menuService;

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


}
