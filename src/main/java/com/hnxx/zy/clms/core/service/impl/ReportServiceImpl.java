package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.mapper.ReportMapper;
import com.hnxx.zy.clms.core.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: clms
 * @description: 报告service实现类
 * @author: nile
 * @create: 2020-03-24 16:16
 **/
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public void save(Report report) {
        reportMapper.save(report);
    }

    @Override
    public void update(Report report) {
        reportMapper.update(report);
    }

    @Override
    public void deleteById(String reportId) {
        reportMapper.deleteById(reportId);
    }

    @Override
    public Page<Report> getReportByClassId(Page<Report> page) {
        return reportMapper.getReportByClassId(page);
    }

    @Override
    public Page<Report> getReportByGroupId(Page<Report> page) {
        return reportMapper.getReportByGroupId(page);
    }

    @Override
    public Page<Report> getReportByUserId(Page<Report> page) {
        return reportMapper.getReportByUserId(page);
    }
}
