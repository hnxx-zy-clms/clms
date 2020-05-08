package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.core.mapper.ReportMapper;
import com.hnxx.zy.clms.core.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public void deleteById(Integer reportId) {
        reportMapper.deleteById(reportId);
    }

    @Override
    public void deleteAdminById(Integer reportId) { reportMapper.deleteAdminById(reportId); }

    @Override
    public List<Report> getByPage(Page<Report> page) { return reportMapper.getByPage(page);}

    @Override
    public List<Report> getReportByClassesId(Page<Report> page) {
        return reportMapper.getReportByClassesId(page);
    }

    @Override
    public List<Report> getReportByGroupId(Page<Report> page) {
        return reportMapper.getReportByGroupId(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Report> getReportByUserId(Page<Report> page) { return reportMapper.getReportByUserId(page); }

    @Override
    public void addUserReport(Integer userId,Integer reportId) { reportMapper.addUserReport(userId,reportId); }

    @Override
    public List<Report> getToDayAllReport(String startTime, String endTime) {
        return reportMapper.getToDayAllReport(startTime,endTime);
    }

    @Override
    public List<Report> getWeekAllReport(String startTime, String endTime) {
        return reportMapper.getWeekAllReport(startTime,endTime);
    }

    @Override
    public void setReportNotEnable(Report report) {
        reportMapper.setReportNotEnable(report);
    }

    @Override
    public int getTodayUserReport(Integer userId,String nowToday, Integer reportType ,String[] results) {
        return reportMapper.getTodayUserReport(userId,nowToday,reportType,results);
    }

    @Override
    public ReportStatistics  getTodayStatistics(Page<ReportStatistics> page , Integer i) {
        return reportMapper.getTodayStatistics(page, i);
    }

    @Override
    public  List<ReportStatistics> getMainReportInfo(Page<ReportStatistics> page, Integer i) {
        return reportMapper.getMainReportInfo(page,i);
    }

    @Override
    public List<Report> getMinReportInfo(Integer userId) {
        return reportMapper.getMinReportInfo(userId);
    }


}
