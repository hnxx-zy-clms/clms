package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: clms
 * @description: 报告控制类
 * @author: nile
 * @create: 2020-03-24 16:19
 **/
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 新增报告
     * @param report
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Report report){
        reportService.save(report);
        return new Result<>("添加成功");
    }

    /**
     * 更新报告
     * @param report
     * @return
     */
    @PostMapping("/update")
    public Result<Object> update(@RequestBody Report report){
        reportService.update(report);
        return new Result<>("更新成功");
    }



}
