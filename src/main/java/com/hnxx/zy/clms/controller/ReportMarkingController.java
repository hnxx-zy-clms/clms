package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.entity.ReportMarking;
import com.hnxx.zy.clms.core.service.ReportMarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: clms
 * @description: 报告批阅实体类
 * @author: nile
 * @create: 2020-03-24 16:20
 **/
@RestController
@RequestMapping("/reportMarking")
public class ReportMarkingController {

    @Autowired
    private ReportMarkingService reportMarkingService;

    /**
     *组长获取本组未批阅报告
     * @param page
     * @return
     */
    @PostMapping("/getGroupMarking")
    public Result<Page<Report>> getGroupMarking(@RequestBody Page<Report> page){
        List<Report> reports = reportMarkingService.getGroupMarking(page);
        page.setList(reports);
        page.setTotalCount(reports.size());
        page.pagingDate();
        return new Result<>(page);
    }

    @PostMapping("/setGroupMarkings")
    public Result<Object> setGroupMarking(@RequestBody List<ReportMarking> reportMarkings){
        reportMarkingService.setCheck(reportMarkings.get(0).getReportId());
        reportMarkingService.setGroupMarking(reportMarkings);
        return new Result<>("成功");
    }

}
