package com.hnxx.zy.clms.core.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classes implements Serializable {

    private Integer classesId;

    /**
     * 班级名称
     */
    @Excel(name = "班级名称", orderNum = "1")
    private String classesName;

    /**
     * 班级是否开启
     */
    private Integer classesStates;

    /**
     * 所属学院id
     */
    @Excel(name = "所属学院id", orderNum = "2")
    private Integer classesCollegeId;

    /**
     * 所属学院名称
     */
    private String collegeName;
}
