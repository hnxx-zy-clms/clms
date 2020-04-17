package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


/**
 * @program: clms
 * @description: 报告批阅内容实体
 * @author: nile
 * @create: 2020-03-24 15:56
 **/
@Data
public class ReportMarking {

    /**
     * 批阅ID
     */
    private Integer markingId;

    /**
     * 报告ID
     */
    private Integer reportId;

    /**
     * 组长评分
     */
    private Integer groupLeaderScore;

    /**
     * 组长评语
     */
    private String groupLeaderComment;

    /**
     * 组批阅人用户名
     */
    private String groupName;

    /**
     * 组长写批阅的时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date groupTime;


    /**
     * 班长评分
     */
    private Integer monitorScore;

    /**
     * 班长评语
     */
    private String monitorComment;

    /**
     * 班长批阅人用户名
     */
    private String monitorName;

    /**
     * 班长写批阅的时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date monitorTime;

    /**
     * 教师评分
     */
    private Integer teacherScore;

    /**
     * 教师评语
     */
    private String teacherComment;

    /**
     * 教师用户名
     */
    private String teacherName;

    /**
     * 教师写批阅的时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date teacherTime;


}
