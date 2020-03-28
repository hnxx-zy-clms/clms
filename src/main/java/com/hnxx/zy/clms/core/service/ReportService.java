package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;

import java.util.List;

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
    void deleteById(Integer reportId);

    /**
     * 根据user_classes_id和report_type查找班级报告
     */
    List<Report> getReportByClassesId(Page<Report> page);

    /**
     * 根据user_group_id和report_type查找组报告
     */
    List<Report> getReportByGroupId(Page<Report> page);

    /**
     * 根据user_id和report_type查找个人报告
     */
    List<Report> getReportByUserId(Page<Report> page);

    /**
     * 保存报告后添加cl_user_report表的数据
     * @param userId
     * @param reportId
     */
    void addUserReport(Integer userId,Integer reportId);

    /**
     * 获取指定时间段内的所有日报
     * @param startTime
     * @param endTime
     * @return
     */
    List<Report> getToDayAllReport(String startTime,String endTime);

    /**
     * 获取指定时间段内的所有日报
     * @param startTime
     * @param endTime
     * @return
     */
    List<Report> getWeekAllReport(String startTime,String endTime);

    /**
     * 设置报告状态为NotEnable 不可删除或更改
     * @param report
     */
    void setReportNotEnable(Report report);

    /**
     * 获取数据库用户今日是否存在报告
     * @param nowToday
     * @return
     */
    Report getTodayUserReport(Integer userId,String nowToday,Integer reportType);
}
