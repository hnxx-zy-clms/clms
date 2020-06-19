package com.hnxx.zy.clms.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: clms
 * @description: 报告数据统计实体类
 * @author: nile
 * @create: 2020-04-15 10:52
 **/
@Data
public class ReportStatistics implements Serializable {

    private String type;

    private String state;

    private Float value = 0f;

}
