package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.ExceUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @PostMapping(value = "all/{page}/{size}/{className}")
    public Result<PageInfo> findAllClassesByPage(@PathVariable("page") int page,
                                             @PathVariable("size") int size,
                                                 @PathVariable("className") String className){
        PageInfo pages = classesService.findAllClassesByPage(page, size,className);
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

    @PutMapping("delete/{id}")
    public Result<String> deleteClassesById(@PathVariable("id") int id){
        classesService.deleteClassesById(id);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @GetMapping("findClassById/{id}")
    public Result<Classes> findClassById(@PathVariable("id") int id){
        Classes classes = classesService.findClassById(id);
        return new Result<>(classes);
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

    @GetMapping("/exportExcel")
    public void export(HttpServletResponse response) {
        List<Classes> list = classesService.findAll();
        ExceUtils.exportExcel(list, "班级", "班级", Classes.class, "班级.xls", response);

    }

    @PostMapping("/importExcel")
    public void importExcel(@RequestParam("file") MultipartFile file) {
        List<Classes> list = ExceUtils.importExcel(file, 1, 1, Classes.class);
        for (Classes classes : list){
            classesService.save(classes);
        }
        System.out.println(list);
    }

    @PostMapping("alter")
    public Result alter(@RequestBody Classes classes){
        classesService.alter(classes);
        return new Result<>(ResultEnum.SUCCESS);
    }
}
