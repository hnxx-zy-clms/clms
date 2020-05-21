package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.entity.ReportMarking;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.core.mapper.ReportMarkingMapper;
import com.hnxx.zy.clms.core.service.ReportMarkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: clms
 * @description: 报告批阅service实现类
 * @author: nile
 * @create: 2020-03-24 16:17
 **/
@Service
public class ReportMarkingServiceImpl implements ReportMarkingService {

    @Autowired
    private ReportMarkingMapper reportMarkingMapper;

    @Override
    public List<ReportMarking> getAllMarking(Page<ReportMarking> page) {
        return reportMarkingMapper.getAllMarking(page);
    }

    @Override
    public void deleteAdminById(Integer markingId) {
        reportMarkingMapper.deleteAdminById(markingId);
    }

    @Override
    public List<Report> getNotMarkingReport(Page<Report> page) {
        return reportMarkingMapper.getNotMarkingReport(page);
    }

    @Override
    public void setGroupMarking(List<ReportMarking> reportMarkings) {
        reportMarkingMapper.setGroupMarking(reportMarkings);
    }

    @Override
    public void setClassesMarking(List<ReportMarking> reportMarkings) {
        reportMarkingMapper.setClassesMarking(reportMarkings);
    }

    @Override
    public void setTeacherMarking(List<ReportMarking> reportMarkings) {
        reportMarkingMapper.setTeacherMarking(reportMarkings);
    }

    @Override
    public  List<Report> getMarkingReport(Page<Report> page){
        return reportMarkingMapper.getMarkingReport(page);
    }

    @Override
    public ReportMarking getUserMarkingById(Integer id) {
        return reportMarkingMapper.getUserMarkingById(id);
    }

    @Override
    public ReportStatistics getAvgReportScore(Page<ReportStatistics> page) {
        return reportMarkingMapper.getAvgReportScore(page);
    }

    @Override
    public ReportStatistics getReportScore(Page<ReportStatistics> page) {
        return reportMarkingMapper.getReportScore(page);
    }
}
