package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @program: clms
 * @description: 报告实体类
 * @author: nile
 * @create: 2020-03-24 15:47
 **/
@Data
public class Report {

    /**
     * 报告Id
     */
    private Integer reportId;

    /**
     * 报告类型: 0日报  1周报
     */
    private Integer reportType;

    /**
     * 用户名
     */
    private String name;

    /**
     * 组名
     */
    private String userGroupId;

    /**
     * 班级名
     */
    private String userClassesId;

    /**
     * 工作内容
     */
    private String workContent;

    /**
     * 遇到的困难
     */
    private String difficulty;

    /**
     * 解决方法
     */
    private String solution;

    /**
     * 心得体会
     */
    private String experience;

    /**
     * 后续计划
     */
    private String plan;

    /**
     * 写日报的时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    /**
     * 更新报告的时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updatedTime;

    /**
     * 报告读写状态：0 可编辑 1 不可编辑
     */
    private Integer isEnabled;

    /**
     * 报告是否存在： 0未删除 1 已删除
     */
    private Integer isDeleted;

    /**
     * 组长报告检查状态： 0 未检查 1 已检查
     */
    private Integer isChecked;

    /**
     * 班长报告检查状态： 0 未检查 1 已检查
     */
    private Integer isClassesChecked;

    /**
     * 老师报告检查状态： 0 未检查 1 已检查
     */
    private Integer isTeacherChecked;

}
