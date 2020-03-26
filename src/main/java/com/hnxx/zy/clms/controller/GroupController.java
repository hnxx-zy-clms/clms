package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.service.CollegeService;
import com.hnxx.zy.clms.core.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping(value = "all")
    public Result<PageInfo> findAllByPage(@RequestParam(required = false,defaultValue = "1") int page,
                                                 @RequestParam(required = false,defaultValue = "5") int size){
        PageInfo pages = groupService.findAllByPage(page, size);
        return new Result<>(pages);
    }

    @PostMapping("save")
    public Result save(@RequestBody Group group){
        groupService.save(group);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("update")
    public Result<String> updateClasses(int id){
        groupService.updateClasses(id);
        return new Result<>(ResultEnum.SUCCESS);
    }

}
