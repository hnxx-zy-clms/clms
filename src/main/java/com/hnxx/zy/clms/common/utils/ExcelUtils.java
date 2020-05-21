package com.hnxx.zy.clms.common.utils;

import com.hnxx.zy.clms.core.entity.Report;
import lombok.Data;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @program: clms
 * @description: 报告Excle表导出工具类
 * @author: nile
 * @create: 2020-04-04 14:59
 **/
@Data
public class ExcelUtils {

    /**
     * sheet名
     */
    private String sheetName;

    /**
     * 导出表名
     */
    private String fileName;

    /**
     * 导出数据
     */
    private List<Report> list;

    /**
     * response流
     */
    private HttpServletResponse response;

    public void start() throws IOException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
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

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);

        String[] headers = {"报告ID","创建时间","班级名","组名", "用户名","工作内容", "遇到的困难","解决方法","心得体会","后续计划"};

        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headstyle);
        }
        //在表中存放查询到的数据放入对应的列
        for (Report report : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.setHeight((short) 3000);
            HSSFCell cell = row1.createCell(0);
            cell.setCellValue(report.getReportId());
            cell.setCellStyle(headstyle);
            utils(row1,1,headstyle,df.format(report.getCreatedTime()));
            HSSFCell cell1 = row1.createCell(2);
            cell1.setCellValue(report.getUserClassesId());
            cell1.setCellStyle(headstyle);
            HSSFCell cell2 = row1.createCell(3);
            cell2.setCellValue(report.getUserGroupId());
            cell2.setCellStyle(headstyle);
            utils(row1,4,headstyle,report.getName());
            utils(row1,5,headstyle,report.getWorkContent());
            utils(row1,6,headstyle,report.getDifficulty());
            utils(row1,7,headstyle,report.getSolution());
            utils(row1,8,headstyle,report.getExperience());
            utils(row1,9,headstyle,report.getPlan());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    private void utils(HSSFRow row, int column, HSSFCellStyle headstyle, String  obj) {
        HSSFCell cell = row.createCell(column);
        HSSFRichTextString text = new HSSFRichTextString(obj);
        cell.setCellValue(text);
        cell.setCellStyle(headstyle);
    }


}
