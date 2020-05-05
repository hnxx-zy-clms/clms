package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.DateUtils;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.entity.ReportMarking;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.core.service.ReportMarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     *管理员获取批阅报告
     * @param page
     * @return
     */
    @PostMapping("/getAllMarking")
    public Result<Page<ReportMarking>> getAllMarking(@RequestBody Page<ReportMarking> page){
        page.setSortColumn(StringUtils.upperCharToUnderLine(page.getSortColumn()));
        List<ReportMarking> reports = reportMarkingService.getAllMarking(page);
        page.setList(reports);
        page.setTotalCount(reports.size());
        page.pagingDate();
        return new Result<>(page);
    }

    /**
     * 管理员清空批阅数据
     * @param markingId
     * @return
     */
    @DeleteMapping("/deleteAdmin/{id}")
    public Result<Object> deleteAdmin(@PathVariable("id") Integer markingId) {
        reportMarkingService.deleteAdminById(markingId);
        return new Result<>("删除成功");
    }

    /**
     *组长获取本组未批阅报告
     * @param page
     * @return
     */
    @GetMapping("/getGroupMarking")
    public Result<Page<Report>> getGroupMarking(@RequestBody Page<Report> page){
        List<Report> reports = reportMarkingService.getGroupMarking(page);
        page.setList(reports);
        page.setTotalCount(reports.size());
        page.pagingDate();
        return new Result<>(page);
    }

    /**
     * 组长提交批阅数据
     * @param reportMarkings
     * @return
     */
    @PostMapping("/setGroupMarkings")
    public Result<Object> setGroupMarking(@RequestBody List<ReportMarking> reportMarkings){
        reportMarkingService.setGroupMarking(reportMarkings);
        return new Result<>("成功");
    }

    /**
     * 班长提交批阅数据
     * @param reportMarkings
     * @return
     */
    @PostMapping("/setClassesMarkings")
    public Result<Object> setClassesMarking(@RequestBody List<ReportMarking> reportMarkings){
        reportMarkingService.setClassesMarking(reportMarkings);
        return new Result<>("成功");
    }

    /**
     * 教师提交批阅数据
     * @param reportMarkings
     * @return
     */
    @PostMapping("/setTeacherMarkings")
    public Result<Object> setTeacherMarking(@RequestBody List<ReportMarking> reportMarkings){
        reportMarkingService.setTeacherMarking(reportMarkings);
        return new Result<>("成功");
    }

    /**
     * 用户获取自己的批阅
     * @param reportId
     * @param userName
     *
     * @return
     */
    @GetMapping("/getMyMarking")
    public Result<List<ReportMarking>> getMyMarking(@RequestParam("reportId") Integer reportId,@RequestParam("userName") String userName){
        List<ReportMarking> reportMarkings = reportMarkingService.getMyMarking(reportId,userName);
        return new Result<>(reportMarkings);
    }

    /**
     * 学生感觉报告ID获取批阅数据
     * @param id
     * @return
     */
    @GetMapping("/getUserMarkingById/{id}")
    public Result<ReportMarking> getUserMarking(@PathVariable("id") Integer id){
        ReportMarking reportMarking = reportMarkingService.getUserMarkingById(id);
        return new Result<>(reportMarking);
    }

    @PostMapping("/getMarkingScore")
    public Result<Page<ReportStatistics>>getMarkingScore(@RequestBody Page<ReportStatistics> page){
        DateUtils dateUtils =new DateUtils();
        String[] c = dateUtils.getBeforeSevenDay();
        List<ReportStatistics> reportStatisticsList = new ArrayList<>();
        for (String time : c) {
            page.params.put("time",time);
            reportStatisticsList.add(reportMarkingService.getAvgReportScore(page));
            if(reportMarkingService.getReportScore(page) == null){
                ReportStatistics reportStatistics =new ReportStatistics();
                reportStatistics.setType(time);
                reportStatistics.setState((String)page.params.get("userName"));
                reportStatistics.setValue((float)0);
                reportStatisticsList.add(reportStatistics);
            }else {
                reportStatisticsList.add(reportMarkingService.getReportScore(page));
            }
        }
        page.setList(reportStatisticsList);
        return new Result<>(page);
    }

}
