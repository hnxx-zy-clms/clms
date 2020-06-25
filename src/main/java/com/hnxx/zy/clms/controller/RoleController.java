package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Role;
import com.hnxx.zy.clms.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 南街北巷
 * @data 2020/6/3 4:20
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @PostMapping("/get/byPage")
    public Result<PageInfo<Role>> getByPage(@RequestBody Page page) {
        PageInfo<Role> pages = roleService.getAllRole(page.getCurrentPage(), page.getPageSize());
        return new Result<>(pages);
    }

    /**
     * 增加角色
     *
     * @return
     */
    @PostMapping("/add")
    public Result<Object> insertUser(@RequestBody Role role) {
        Role oldRole = roleService.getById(role.getRoleId());
        if (role.getRoleName() == oldRole.getRoleName()) {
            return new Result<>("已重复保存");
        } else {
            roleService.insertRole(role);
            return new Result<>("保存成功");
        }
    }

    /**
     *
     * 根据id查询角色
     * @param role
     * @return
     */
    @PostMapping("/get/byId")
    public Result<Role> getById(Role role){
        role = roleService.getById(role.getRoleId());
        return new Result<>(role);
    }
}
