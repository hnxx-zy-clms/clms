package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.service.ClassesService;
import com.hnxx.zy.clms.core.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @GetMapping(value = "all/{page}/{size}")
    public Result<PageInfo> findAllByPage(@PathVariable("page") int page,
                                          @PathVariable("size") int size){
        PageInfo pages = collegeService.findAllByPage(page, size);
        return new Result<>(pages);
    }

    @PostMapping("save")
    public Result save(@RequestBody College college){
        collegeService.save(college);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("update/{id}/{type}")
    public Result<String> updateClasses(@PathVariable("id") int id ,@PathVariable("type") int type){
        collegeService.updateClasses(id,type);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("updateIds")
    public Result<String> updateClasses(@RequestBody List<Integer> ids){
        collegeService.updateIdsClasses(ids);
        return new Result<>(ResultEnum.SUCCESS);
    }

}
