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

import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping(value = "all/{page}/{size}")
    public Result<PageInfo> findAllByPage(@PathVariable("page") int page,
                                          @PathVariable("size") int size){
        PageInfo pages = groupService.findAllByPage(page, size);
        return new Result<>(pages);
    }

    @PostMapping("save")
    public Result save(@RequestBody Group group){
        groupService.save(group);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("update/{id}/{type}")
    public Result<String> updateClasses(@PathVariable("id") int id ,@PathVariable("type") int type){
        groupService.updateClasses(id,type);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("updateIds")
    public Result<String> updateClasses(@RequestBody List<Integer> ids){
        groupService.updateIdsClasses(ids);
        return new Result<>(ResultEnum.SUCCESS);
    }

}
