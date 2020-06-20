package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.ExceUtils;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.service.CollegeService;
import com.hnxx.zy.clms.core.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping(value = "all/{page}/{size}/{groupName}")
    public Result<PageInfo> findAllByPage(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable("groupName") String groupName){
        PageInfo pages = groupService.findAllByPage(page, size, groupName);
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

    @PutMapping("delete/{id}")
    public Result<String> deleteClassesById(@PathVariable("id") int id){
        groupService.deleteClassesById(id);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @GetMapping("findClassById/{id}")
    public Result<Group> findClassById(@PathVariable("id") int id){
        Group group = groupService.findClassById(id);
        return new Result<>(group);
    }

    @GetMapping("/exportExcel")
    public void export(HttpServletResponse response) {
        List<Group> groups = groupService.findAll();
        ExceUtils.exportExcel(groups, "组", "组", Group.class, "商品.xls", response);

    }

    @PostMapping("/importExcel")
    public void importExcel2(@RequestParam("file") MultipartFile file) {
        List<Group> groups = ExceUtils.importExcel(file, 1, 1, Group.class);
        for (Group group : groups){
            groupService.save(group);
        }
        System.out.println(groups);
    }

    @PostMapping("alter")
    public Result alter(@RequestBody Group group){
        groupService.alter(group);
        return new Result<>(ResultEnum.SUCCESS);
    }
}
