package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.entity.Position;
import com.hnxx.zy.clms.core.service.GroupService;
import com.hnxx.zy.clms.core.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping(value = "all")
    public Result<PageInfo> findAllByPage(@RequestParam(required = false,defaultValue = "1") int page,
                                          @RequestParam(required = false,defaultValue = "5") int size){
        PageInfo pages = positionService.findAllByPage(page, size);
        return new Result<>(pages);
    }

    @PostMapping("save")
    public Result save(@RequestBody Position position){
        positionService.save(position);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("update")
    public Result<String> updateClasses(int id){
        positionService.updateClasses(id);
        return new Result<>(ResultEnum.SUCCESS);
    }


}
