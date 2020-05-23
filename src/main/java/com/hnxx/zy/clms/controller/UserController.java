package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.mapper.UserMapper;
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

    @Autowired(required = true)
    private UserMapper userMapper;

    /**
     * 根据用户id查询组名
     *
     * @return
     */
    @PostMapping("/selectByGroup/{id}")
    public String selectByGroupId(@PathVariable("id") Integer id) {
        String groupId = userMapper.selectByGroupId(id);
        return groupId;
    }

    /**
     * 根据用户id或用户名查询组名
     * @param user
     * @return
     */
    @PostMapping("/get/byGroup")
    public String selectByGroup(@RequestBody User user){
        String group = userMapper.getByGroup(user);
        return group;
    }

}
