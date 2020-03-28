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
     * 修改报告批阅状态
     * @param reportId
     */
    void setCheck(Integer reportId);

    /**
     * 学生获取批阅信息
     * @param page
     * @return
     */
    List<ReportMarking> getMyMarking(Page<ReportMarking> page);

}
