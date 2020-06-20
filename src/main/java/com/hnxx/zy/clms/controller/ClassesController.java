package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.ClassSex;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.ClassesReport;
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

    @GetMapping(value = "all/{page}/{size}")
    public Result<PageInfo> findAllClassesByPage(@PathVariable("page") int page,
                                             @PathVariable("size") int size){

        PageInfo pages = classesService.findAllClassesByPage(page, size);
        return new Result<>(pages);
    }

    @PostMapping("save")
    public Result save(@RequestBody Classes classes){
        classesService.save(classes);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("update/{id}/{type}")
    public Result<String> updateClasses(@PathVariable("id") int id ,@PathVariable("type") int type){
        classesService.updateClasses(id,type);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("updateIds")
    public Result<String> updateClasses(@RequestBody List<Integer> ids){
        classesService.updateIdsClasses(ids);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @GetMapping("report")
    public Result<List<ClassesReport>> report(){
        List<ClassesReport> classesReportList = classesService.report();
        return new Result<>(classesReportList);
    }

    @GetMapping("findSexPercent")
    public Result<List<ClassSex>> findSexPercent() {
        List<ClassSex> classSexList = classesService.findSexPercent();
        return new Result<>(classSexList);
    }
}
