package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.entity.UserSearch;
import com.hnxx.zy.clms.core.mapper.UserMapper;
import com.hnxx.zy.clms.core.service.UserService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/5/9 9:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /**
     * 根据用户id查询组名
     *
     * @return
     */
    @PostMapping("/selectByGroup/{id}")
    public String selectByGroupId(@PathVariable("id") Integer id) {
        String groupId = userService.selectByGroupId(id);
        return groupId;
    }

    /**
     * 根据用户id或用户名查询组名
     * @param user
     * @return
     */
    @PostMapping("/get/byGroup")
    public List<User> selectByGroup(@RequestBody User user){
        List<User> users = userService.getByGroup(user);
        return users;
    }

}
