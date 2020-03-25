package com.hnxx.zy.clms.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classes implements Serializable {

    private int classesId;

    /**
     * 班级名称
     */
    private String classesName;

    /**
     * 班级是否开启
     */
    private int classesStates;

}
