package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.ExceUtils;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("college")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    @GetMapping(value = "all/{page}/{size}/{collegeName}")
    public Result<PageInfo> findAllByPage(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable("collegeName") String collegeName){
        PageInfo pages = collegeService.findAllByPage(page, size,collegeName);
        return new Result<>(pages);
    }

    @PostMapping("findAll")
    public Result<List<College>> findAll(){
        List<College> colleges = collegeService.findAll();
        return new Result<>(colleges);
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

    @PutMapping("delete/{id}")
    public Result<String> deleteClassesById(@PathVariable("id") int id){
        collegeService.deleteClassesById(id);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @GetMapping("findClassById/{id}")
    public Result<College> findClassById(@PathVariable("id") int id){
        College College = collegeService.findClassById(id);
        return new Result<>(College);
    }

    @GetMapping("/exportExcel")
    public void export(HttpServletResponse response) {
        List<College> colleges = collegeService.findAll();
        ExceUtils.exportExcel(colleges, "学院", "学院", College.class, "学院.xls", response);

    }

    @PostMapping("/importExcel")
    public void importExcel2(@RequestParam("file") MultipartFile file) {
        List<College> colleges = ExceUtils.importExcel(file, 1, 1, College.class);
        for (College college : colleges){
            collegeService.save(college);
        }
        System.out.println(colleges);
    }

    @PostMapping("alter")
    public Result alter(@RequestBody College college){
        collegeService.alter(college);
        return new Result<>(ResultEnum.SUCCESS);
    }
}
