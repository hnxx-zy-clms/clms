package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Commission;
import com.hnxx.zy.clms.core.service.CommissionService;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/22 12:56
 * @version: 1.0
 * @desc:
 */
@RestController
@RequestMapping("/commission")
public class CommissionController {
    private static Logger logger = LoggerFactory.getLogger(CommissionController.class);

    @Autowired
    private CommissionService commissionService;

    /**
     * 保存今日代办
     *
     * @param commission
     * @return
     */
    @PostMapping("/savecommission")
    public Result saveCommission(@RequestBody Commission commission) {
        commissionService.saveCommission(commission);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 完成今日代办
     *
     * @param id
     * @return
     */
    @PutMapping("/setdid/{id}")
    public Result setDid(@PathVariable("id") Integer id) {
        commissionService.setDid(id);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 删除今日代办
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deletecom/{id}")
    public Result deleteCom(@PathVariable("id") Integer id) {
        commissionService.deleteCom(id);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 获取指定日期的今日代办
     *
     * @param id
     * @param time
     * @return
     */
    @GetMapping("/getcom/{id}/{time}")
    public Result getComByIdAndTime(@PathVariable("id") Integer id, @PathVariable("time") String time) {
        List<Commission> commissions = commissionService.getComByIdAndTime(id, time);
        return new Result<>(commissions);
    }


}
