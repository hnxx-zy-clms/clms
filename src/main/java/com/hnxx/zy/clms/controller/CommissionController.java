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
    @PostMapping("/saveCommission")
    public Result saveCommission(@RequestBody Commission commission) {
        commissionService.saveCommission(commission);
        return new Result("保存成功");
    }

    /**
     * 将代办状态设置为已完成
     *
     * @param id
     * @return
     */
    @PutMapping("/setIsDo/{id}")
    public Result setIsDo(@PathVariable("id") Integer id) {
        commissionService.setDid(id);
        return new Result(ResultEnum.SUCCESS);
    }

    /**
     * 删除今日代办
     *
     * @param id
     * @return
     */
    @PutMapping("/deleteCommission/{id}")
    public Result deleteCom(@PathVariable("id") Integer id) {
        commissionService.deleteCom(id);
        return new Result("删除成功");
    }

    /**
     * 获取指定日期的今日代办
     *
     * @param id
     * @param time
     * @return
     */
    @GetMapping("/getCommissionByIdAndTime/{id}/{time}")
    public Result getComByIdAndTime(@PathVariable("id") Integer id, @PathVariable("time") String time) {
        List<Commission> commissions = commissionService.getComByIdAndTime(id, time);
        return new Result<>(commissions);
    }


}
