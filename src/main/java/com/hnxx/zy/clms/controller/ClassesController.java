package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @GetMapping(value = "all")
    public Result<PageInfo> findAllClassesByPage(@RequestParam(required = false,defaultValue = "1") int page,
                                             @RequestParam(required = false,defaultValue = "5") int size){

        PageInfo pages = classesService.findAllClassesByPage(page, size);
        return new Result<>(pages);
    }

    @PostMapping("save")
    public Result save(@RequestBody Classes classes){
        classesService.save(classes);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("update")
    public Result<String> updateClasses(int id){
        classesService.updateClasses(id);
        return new Result<>(ResultEnum.SUCCESS);
    }

}
