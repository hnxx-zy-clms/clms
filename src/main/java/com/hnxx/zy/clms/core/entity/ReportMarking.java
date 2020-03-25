package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @program: clms
 * @description: 报告批阅内容实体
 * @author: nile
 * @create: 2020-03-24 15:56
 **/
@Data
public class ReportMarking {

    /**
     * 报告ID
     */
    private Integer reportId;

    /**
     * 批阅类型
     */
    private String keys;

    /**
     * 批阅内容
     */
    private String values;

    /**
     * 批阅人
     */
    private String username;

    /**
     * 写批阅的时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Data createTime;

}
