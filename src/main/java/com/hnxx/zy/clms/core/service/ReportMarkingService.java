package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.entity.ReportMarking;

import java.util.List;

/**
 * @program: clms
 * @description: 报告批阅Service实现类
 * @author: nile
 * @create: 2020-03-24 16:15
 **/
public interface ReportMarkingService {

    /**
     * 返回未批阅的报告
     * @param page
     * @return
     */
    List<Report> getGroupMarking(Page<Report> page);

    /**
     * 组长提交批阅报告
     * @param reportMarkings
     */
    void setGroupMarking(List<ReportMarking> reportMarkings);

    /**
     * 修改组长报告批阅状态
     * @param reportId
     */
    void setCheck(Integer reportId);

    /**
     * 修改班长报告批阅状态
     * @param reportId
     */
    void setClassesCheck(Integer reportId);

    /**
     * 修改教师报告批阅状态
     * @param reportId
     */
    void setTeacherCheck(Integer reportId);

    /**
     * 获取批阅信息
     * @param reportId
     * @param userName
     * @return
     */
    List<ReportMarking> getMyMarking(Integer reportId,String userName);

    /**
     ** 学生查询报告批阅
     * @param page
     * @return
     */
    List<ReportMarking> getUserMarking(Page<ReportMarking> page);

}
