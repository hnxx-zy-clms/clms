package com.hnxx.zy.clms.controller;

import com.github.pagehelper.PageInfo;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.ExceUtils;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.entity.Position;
import com.hnxx.zy.clms.core.service.GroupService;
import com.hnxx.zy.clms.core.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping(value = "all/{page}/{size}/{positionName}")
    public Result<PageInfo> findAllByPage(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable("positionName") String positionName){
        PageInfo pages = positionService.findAllByPage(page, size,positionName);
        return new Result<>(pages);
    }

    @PostMapping("save")
    public Result save(@RequestBody Position position){
        positionService.save(position);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("update/{id}/{type}")
    public Result<String> updateClasses(@PathVariable("id") int id ,@PathVariable("type") int type){
        positionService.updateClasses(id,type);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("updateIds")
    public Result<String> updateClasses(@RequestBody List<Integer> ids){
        positionService.updateIdsClasses(ids);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @PutMapping("delete/{id}")
    public Result<String> deleteClassesById(@PathVariable("id") int id){
        positionService.deleteClassesById(id);
        return new Result<>(ResultEnum.SUCCESS);
    }

    @GetMapping("findClassById/{id}")
    public Result<Position> findClassById(@PathVariable("id") int id){
        Position position = positionService.findClassById(id);
        return new Result<>(position);
    }

    @GetMapping("/exportExcel")
    public void export(HttpServletResponse response) {
        List<Position> positions = positionService.findAll();
        ExceUtils.exportExcel(positions, "商品", "商品", Position.class, "商品.xls", response);

    }

    @PostMapping("/importExcel")
    public void importExcel2(@RequestParam("file") MultipartFile file) {
        List<Position> positions = ExceUtils.importExcel(file, 1, 1, Position.class);
        for (Position position : positions){
            positionService.save(position);
        }
        System.out.println(positions);
    }

    @PostMapping("alter")
    public Result alter(@RequestBody Position position){
        positionService.alter(position);
        return new Result<>(ResultEnum.SUCCESS);
    }
}
