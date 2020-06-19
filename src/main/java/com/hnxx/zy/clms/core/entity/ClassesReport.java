package com.hnxx.zy.clms.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassesReport implements Serializable {

    /**
     * 班级名称
     */
    private String item;

    /**
     * 每个班级人数
     */
    private Integer count;

    /**
     * 人数百分比
     */
    private Double percent;


}
