package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.service.ReportService;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    /**
     * 新增报告
     * @param report
     * @return
     */
    @PostMapping("/save")
    public Result<Object> save(@RequestBody Report report){
        reportService.save(report);
        SysUser userId=userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
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
        reportService.deleteById(reportId);
        return new Result<>("删除成功");
    }

    /**
     * 根据user_id、日期和reportType分页查询报告
     * @param page
     */
    @PostMapping("/getByUserId")
    public Result<Page<Report>> getByUserId(@RequestBody Page<Report> page){
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
     * @return
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
     * 导出报告
     * @param response
     * @throws IOException
     */
    @PostMapping("/userExcelDownloads")
    public void downloadAllClassmate(@RequestBody Page<Report> page,HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("报告信息表");

        List<Report> reports = reportService.getReportByUserId(page);
        //设置要导出的文件的名字
        String fileName = "reportinfo" + ".xlsx";
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = {"报告ID", "工作内容", "遇到的困难","解决方法","心得体会","后续计划"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (Report report : reports) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(report.getReportId());
            row1.createCell(1).setCellValue(report.getWorkContent());
            row1.createCell(2).setCellValue(report.getDifficulty());
            row1.createCell(3).setCellValue(report.getSolution());
            row1.createCell(4).setCellValue(report.getExperience());
            row1.createCell(5).setCellValue(report.getPlan());
            rowNum++;
        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }



}
