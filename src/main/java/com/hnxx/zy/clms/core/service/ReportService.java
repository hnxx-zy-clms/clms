package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;

/**
 * @program: clms
 * @description: 报告service
 * @author: nile
 * @create: 2020-03-24 16:14
 **/
public interface ReportService {

    /**
     * 保存 添加报告
     */
    void save(Report report);

    /**
     * 修改报告
     */
    void update(Report report);

    /**
     * 根据id删除报告
     */
    void deleteById(String reportId);

    /**
     * 根据user_classes_id和report_type查找班级报告
     */
    Page<Report> getReportByClassId(Page<Report> page);

    /**
     * 根据user_group_id和report_type查找组报告
     */
    Page<Report> getReportByGroupId(Page<Report> page);

    /**
     * 根据user_id和report_type查找个人报告
     */
    Page<Report> getReportByUserId(Page<Report> page);



}
