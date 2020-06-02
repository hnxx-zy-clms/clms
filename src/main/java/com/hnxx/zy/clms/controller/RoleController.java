package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Role;
import com.hnxx.zy.clms.core.entity.UserSearch;
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

    @PostMapping("/get/byPage")
    public Result<PageInfo<Role>> getByPage(@RequestBody Page page){
        PageInfo<Role> pages= roleService.getAllRole(page.getCurrentPage(),page.getPageSize());
        return new Result<>(pages);
    }
}
