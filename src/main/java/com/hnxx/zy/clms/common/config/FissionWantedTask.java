package com.hnxx.zy.clms.common.config;

import com.hnxx.zy.clms.core.entity.Report;
import com.hnxx.zy.clms.core.service.ReportService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: clms
 * @description: 定时器任务类
 * @author: nile
 * @create: 2020-03-27 14:58
 **/
@Component
@Configuration
@EnableScheduling
@CommonsLog
public class FissionWantedTask {

    @Autowired
    private ReportService reportService;

    @Scheduled(cron="0 0 12-22 * * ?")
    public void todayNotEnable(){
        Calendar rightNow = Calendar.getInstance();
        int ti = reportService.getTime();
        if(rightNow.get(Calendar.HOUR_OF_DAY) == ti ){
             DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = df.format(rightNow.getTime());
            List<Report> reports=reportService.getToDayAllReport(startTime+" "+ ti +":00:00" , "2020-03-24");
            for(Report report:reports){
                reportService.setReportNotEnable(report);
            }
        }
    }

    @Scheduled(cron="0 0 12-22 ? * SUN")
    public void weekNotEnable(){
        Calendar rightNow = Calendar.getInstance();
        if(rightNow.get(Calendar.HOUR_OF_DAY) == reportService.getTime()){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String startTime = df.format(rightNow.getTime());
            List<Report> reports=reportService.getWeekAllReport(startTime+" 22:00:00" , "2020-03-24");
            for(Report report:reports){
                reportService.setReportNotEnable(report);
            }
        }
    }

}
