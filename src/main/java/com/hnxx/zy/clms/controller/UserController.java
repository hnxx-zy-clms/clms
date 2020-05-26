package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.utils.Page;
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
     * 根据用户id或用户名查询所有用户
     * @param user
     * @return
     */
    @PostMapping("/get/byGroup")
    public Result<List<UserSearch>> selectByGroup(@RequestBody User user){
        List<UserSearch> users = userService.getByGroup(user);
        return new Result<>(users);
    }

    /**
     * 获取用户详细信息
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/get/byPage")
    public Result<PageInfo<UserSearch>> getUserByPage(@RequestBody Page page){
        PageInfo<UserSearch> userPage= userService.getUserByPage(page.getCurrentPage(), page.getPageSize());
        return new Result<>(userPage);
    }

}
