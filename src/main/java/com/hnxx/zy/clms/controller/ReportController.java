package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.service.ReportService;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        SysUser userId=userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if(rightNow.get(Calendar.HOUR_OF_DAY) >= 22 ){
            return new Result<>("时间已截止");
        }else if(reportService.getTodayUserReport(userId.getUserId(),sdf.format(new Date()),report.getReportType()) != null){
            return new Result<>("数据库已存在报告数据");
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
     * 学生导出报告
     * @param page
     * @param response
     * @throws IOException
     */
    @PostMapping("/userExcelDownloads")
    public void userExcelDownloads(@RequestBody Page<Report> page,HttpServletResponse response) throws IOException {
        SysUser user=userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("报告信息表");
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 8000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 8000);
        sheet.setColumnWidth(6, 8000);
        sheet.setColumnWidth(7, 8000);

        // 设置字体
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("楷体");
        // 字体大小
        headfont.setFontHeightInPoints((short) 10);

        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        // 左右居中
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 上下居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headstyle.setLocked(true);
        // 自动换行
        headstyle.setWrapText(true);
        // 设置单元格的边框颜色．
        headstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        List<Report> reports = reportService.getReportByUserId(page);
        //设置要导出的文件的名字
        String fileName = "userReportInfo" + ".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"报告ID","创建时间", "用户名","工作内容", "遇到的困难","解决方法","心得体会","后续计划"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headstyle);
        }
        //在表中存放查询到的数据放入对应的列
        for (Report report : reports) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.setHeight((short) 3000);
            HSSFCell cell = row1.createCell(0);
            cell.setCellValue(report.getReportId());
            cell.setCellStyle(headstyle);
            HSSFUtils(row1,1,headstyle,df.format(report.getCreatedTime()));
            HSSFUtils(row1,2,headstyle,user.getUserName());
            HSSFUtils(row1,3,headstyle,report.getWorkContent());
            HSSFUtils(row1,4,headstyle,report.getDifficulty());
            HSSFUtils(row1,5,headstyle,report.getSolution());
            HSSFUtils(row1,6,headstyle,report.getExperience());
            HSSFUtils(row1,7,headstyle,report.getPlan());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * 组长导出报告
     * @param page
     * @param response
     * @throws IOException
     */
    @PostMapping("/groupExcelDownloads")
    public void groupExcelDownloads(@RequestBody Page<Report> page,HttpServletResponse response) throws IOException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("报告信息表");
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 3000);
        sheet.setColumnWidth(4, 8000);
        sheet.setColumnWidth(5, 6000);
        sheet.setColumnWidth(6, 8000);
        sheet.setColumnWidth(7, 8000);
        sheet.setColumnWidth(8, 8000);
        // 设置字体
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("楷体");
        // 字体大小
        headfont.setFontHeightInPoints((short) 10);
        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        // 左右居中
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 上下居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headstyle.setLocked(true);
        // 自动换行
        headstyle.setWrapText(true);
        // 设置单元格的边框颜色．
        headstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        List<Report> reports = reportService.getReportByGroupId(page);
        //设置要导出的文件的名字
        String fileName = "groupReportInfo" + ".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"报告ID","创建时间", "组名","用户名","工作内容", "遇到的困难","解决方法","心得体会","后续计划"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headstyle);
        }
        //在表中存放查询到的数据放入对应的列
        for (Report report : reports) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.setHeight((short) 3000);
            HSSFCell cell = row1.createCell(0);
            cell.setCellValue(report.getReportId());
            cell.setCellStyle(headstyle);
            HSSFUtils(row1,1,headstyle,df.format(report.getCreatedTime()));
            HSSFCell cell1 = row1.createCell(2);
            cell1.setCellValue(report.getUserGroupId());
            cell1.setCellStyle(headstyle);
            HSSFUtils(row1,3,headstyle,report.getUserName());
            HSSFUtils(row1,4,headstyle,report.getWorkContent());
            HSSFUtils(row1,5,headstyle,report.getDifficulty());
            HSSFUtils(row1,6,headstyle,report.getSolution());
            HSSFUtils(row1,7,headstyle,report.getExperience());
            HSSFUtils(row1,8,headstyle,report.getPlan());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     *
     * @param page
     * @param response
     * @throws IOException
     */
    @PostMapping("/classesExcelDownloads")
    public void classesExcelDownloads(@RequestBody Page<Report> page,HttpServletResponse response) throws IOException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("报告信息表");
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 3000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 8000);
        sheet.setColumnWidth(6, 6000);
        sheet.setColumnWidth(7, 8000);
        sheet.setColumnWidth(8, 8000);
        sheet.setColumnWidth(9, 8000);

        // 设置字体
        HSSFFont headfont = workbook.createFont();
        headfont.setFontName("楷体");
        // 字体大小
        headfont.setFontHeightInPoints((short) 10);

        HSSFCellStyle headstyle = workbook.createCellStyle();
        headstyle.setFont(headfont);
        // 左右居中
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 上下居中
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headstyle.setLocked(true);
        // 自动换行
        headstyle.setWrapText(true);
        // 设置单元格的边框颜色．
        headstyle.setBottomBorderColor(HSSFColor.BLACK.index);
        List<Report> reports = reportService.getReportByClassesId(page);
        //设置要导出的文件的名字
        String fileName = "classesReportInfo" + ".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"报告ID","创建时间","班级名","组名", "用户名","工作内容", "遇到的困难","解决方法","心得体会","后续计划"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headstyle);
        }
        //在表中存放查询到的数据放入对应的列
        for (Report report : reports) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.setHeight((short) 3000);
            HSSFCell cell = row1.createCell(0);
            cell.setCellValue(report.getReportId());
            cell.setCellStyle(headstyle);
            HSSFUtils(row1,1,headstyle,df.format(report.getCreatedTime()));
            HSSFCell cell1 = row1.createCell(2);
            cell1.setCellValue(report.getUserClassesId());
            cell1.setCellStyle(headstyle);
            HSSFCell cell2 = row1.createCell(3);
            cell2.setCellValue(report.getUserGroupId());
            cell2.setCellStyle(headstyle);
            HSSFUtils(row1,4,headstyle,report.getUserName());
            HSSFUtils(row1,5,headstyle,report.getWorkContent());
            HSSFUtils(row1,6,headstyle,report.getDifficulty());
            HSSFUtils(row1,7,headstyle,report.getSolution());
            HSSFUtils(row1,8,headstyle,report.getExperience());
            HSSFUtils(row1,9,headstyle,report.getPlan());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    private void HSSFUtils(HSSFRow row,int column,HSSFCellStyle headstyle, String  obj) {
        HSSFCell cell = row.createCell(column);
        HSSFRichTextString text = new HSSFRichTextString(obj);
        cell.setCellValue(text);
        cell.setCellStyle(headstyle);
    }


}
