package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.*;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.service.ReportService;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private UserService userService;

    private Calendar rightNow = Calendar.getInstance();

    /**
     * 新增报告
     * @param report
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Report report) throws ParseException {
        User userId=userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        DateUtils dateUtils =new DateUtils();
        if(rightNow.get(Calendar.HOUR_OF_DAY) >= 22 ){
            return new Result<>(401,"时间已截止");
        }else if(report.getReportType() == 0){
            if(reportService.getTodayUserReport(userId.getUserId(),sdf.format(new Date()),0, null) != 0) {
                return new Result<>(401,"已存在报告数据");
            }
        }else if (report.getReportType() == 1){
            String[] results = dateUtils.getDateWeek(sdf.format(new Date()));
            if(reportService.getTodayUserReport(userId.getUserId(),null,1,results) != 0) {
                return new Result<>(401,"已存在报告数据");
            }
        }
        reportService.save(report);
        reportService.addUserReport(userId.getUserId(),report.getReportId());
        return new Result<>("添加成功");
    }

    /**
     * 更新报告
     * @param report
     * @return
     */
    @PutMapping("/update")
    public Result<Object> update(@RequestBody Report report){
        if(rightNow.get(Calendar.HOUR_OF_DAY) >= 22 ){
            return new Result<>(401,"时间已截止");
        }
        reportService.update(report);
        return new Result<>("更新成功");
    }

    /**
     * 删除报告
     * @param reportId
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable("id") Integer reportId){
        if(rightNow.get(Calendar.HOUR_OF_DAY) >= 22 ){
            return new Result<>(401,"时间已截止");
        }
        reportService.deleteById(reportId);
        return new Result<>("删除成功");
    }

    /**
     * 管理员删除报告
     * @param reportId
     * @return
     */
    @DeleteMapping("/deleteAdmin/{id}")
    public Result<Object> deleteAdmin(@PathVariable("id") Integer reportId){
        reportService.deleteAdminById(reportId);
        return new Result<>("删除成功");
    }
    /**
     * 根据user_id、日期和reportType分页查询报告
     *
     */
    @PostMapping("/getMinReportInfo")
    public Result<List<Report>> getMinReportInfo(){
        User userId=userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Report> reports =reportService.getMinReportInfo(userId.getUserId());
        return new Result<>(reports);
    }

    /**
     * 根据user_id、日期和reportType分页查询报告
     * @param page
     */
    @PostMapping("/getByUserId")
    public Result<Page<Report>> getByUserId(@RequestBody Page<Report> page){
        User userId=userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        page.params.put("userId",userId.getUserId());
        List<Report> reports=reportService.getReportByUserId(page);
        page.setList(reports);
        page.setTotalCount(reports.size());
        page.pagingDate();
        return new Result<>(page);
    }

    /**
     * 根据user_group_id和reportType分页查询报告
     * 根据user_group_id、username、日期和reportType分页查询报告
     * @param page
     * @return
     */
    @PostMapping("/getByGroupId")
    public Result<Page<Report>> getByGroupId(@RequestBody Page<Report> page){
        List<Report> reports=reportService.getReportByGroupId(page);
        page.setList(reports);
        page.setTotalCount(reports.size());
        page.pagingDate();
        return new Result<>(page);
    }

    /**
     * 根据user_classes_id 、user_name、日期和reportType分页查询报告
     * @param page
     * @return m
     */
    @PostMapping("/getByClassesId")
    public Result<Page<Report>> getByClassId(@RequestBody Page<Report> page){
        List<Report> reports=reportService.getReportByClassesId(page);
        page.setList(reports);
        page.setTotalCount(reports.size());
        page.pagingDate();
        return new Result<>(page);
    }

    /**
     * 根据user_classes_id 、user_name、日期和reportType分页查询报告
     * @param page
     * @return m
     */
    @PostMapping("/getByPage")
    public Result<Page<Report>> getByPage(@RequestBody Page<Report> page){
        if(page.getSortColumn() != null) {
            page.setSortColumn(StringUtils.upperCharToUnderLine(page.getSortColumn()));
        }
        List<Report> reports=reportService.getByPage(page);
        page.setList(reports);
        page.setTotalCount(reports.size());
        page.pagingDate();
        return new Result<>(page);
    }


    /**
     * 学生导出报告
     * @param page
     * @param response
     * @throws IOException
     */
    @PostMapping("/userExcelDownloads")
    public void userExcelDownloads(@RequestBody Page<Report> page,HttpServletResponse response) throws IOException {
        String sheetName = "报告信息表";
        String fileName = "userReportInfo" + ".xlsx";
        List<Report> reports = reportService.getReportByUserId(page);
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.setFileName(fileName);
        excelUtils.setList(reports);
        excelUtils.setSheetName(sheetName);
        excelUtils.setResponse(response);
        excelUtils.start();
    }

    /**
     * 组长导出报告
     * @param page
     * @param response
     * @throws IOException
     */
    @PostMapping("/groupExcelDownloads")
    public void groupExcelDownloads(@RequestBody Page<Report> page,HttpServletResponse response) throws IOException {
        String sheetName = "报告信息表";
        String fileName = "groupReportInfo" + ".xlsx";
        List<Report> reports = reportService.getReportByGroupId(page);
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.setFileName(fileName);
        excelUtils.setList(reports);
        excelUtils.setSheetName(sheetName);
        excelUtils.setResponse(response);
        excelUtils.start();
    }

    /**
     *班长长导出报告
     * @param page
     * @param response
     * @throws IOException
     */
    @PostMapping("/classesExcelDownloads")
    public void classesExcelDownloads(@RequestBody Page<Report> page,HttpServletResponse response) throws IOException {
        String sheetName = "报告信息表";
        String fileName = "classesReportInfo" + ".xlsx";
        List<Report> reports = reportService.getReportByClassesId(page);
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.setFileName(fileName);
        excelUtils.setList(reports);
        excelUtils.setSheetName(sheetName);
        excelUtils.setResponse(response);
        excelUtils.start();
    }
    /**
     *班长长导出报告
     * @param page
     * @param response
     * @throws IOException
     */
    @PostMapping("/adminExcelDownloads")
    public void adminExcelDownloads(@RequestBody Page<Report> page,HttpServletResponse response) throws IOException {
        String sheetName = "报告信息表";
        String fileName = "adminReportInfo" + ".xlsx";
        List<Report> reports = reportService.getByPage(page);
        ExcelUtils excelUtils = new ExcelUtils();
        excelUtils.setFileName(fileName);
        excelUtils.setList(reports);
        excelUtils.setSheetName(sheetName);
        excelUtils.setResponse(response);
        excelUtils.start();
    }


    @PostMapping("/getUserReportInfo")
    public Result<Page<ReportStatistics>> getUserNum(@RequestBody Page<ReportStatistics> page) throws ParseException {
        if((Integer)page.params.get("reportType")==1){
            DateUtils dateUtils =new DateUtils();
            if("".equals(page.params.get("time"))){
                SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
                page.params.put("time",formatter.format(new Date()));
            }
            String[] results = dateUtils.getDateWeek((String)page.params.get("time"));
            page.params.put("time",results);
        }
        List<ReportStatistics> reportStatisticsList = new ArrayList<>();
        if (page.params.get("userGroupId")!=null && !"".equals(page.params.get("userGroupId"))){
            int i = userService.getUserNum(page);
            ReportStatistics reportStatistics = reportService.getTodayStatistics(page, i);
            reportStatisticsList.add(reportStatistics);
        } else if ((Integer) page.params.get("isClasses") == 1) {
            int i = userService.getUserNum(page);
            ReportStatistics reportStatistics = reportService.getTodayStatistics(page, i);
            reportStatisticsList.add(reportStatistics);
        }else{
                Integer[] groupIds = userService.getGroupIds(page);
                for (Integer groupId : groupIds) {
                    page.params.put("userGroupId", groupId);
                    int i = userService.getUserNum(page);
                    ReportStatistics reportStatistics = reportService.getTodayStatistics(page, i);
                    reportStatisticsList.add(reportStatistics);
                }
            }
        page.setList(reportStatisticsList);
        return new Result<>(page);
    }

    @PostMapping("/getMainReportInfo")
    public Result<Page<ReportStatistics>> getMainReportInfo(@RequestBody Page<ReportStatistics> page) throws ParseException {
        int i = userService.getUserNum(page);
        if((Integer)page.params.get("reportType")==1){
            DateUtils dateUtils =new DateUtils();
            if("".equals(page.params.get("time"))){
                SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd");
                page.params.put("time",formatter.format(new Date()));
            }
            String[] results = dateUtils.getDateWeek((String)page.params.get("time"));
            page.params.put("time",results);
        }
        List<ReportStatistics> reportStatisticsList=reportService.getMainReportInfo(page,i);
        page.setList(reportStatisticsList);
        return new Result<>(page);
    }

}
