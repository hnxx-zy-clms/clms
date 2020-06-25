package com.hnxx.zy.clms.core.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position implements Serializable {

    private Integer positionId;

    @Excel(name = "职称名称", orderNum = "1")
    private String positionName;

    private Integer positionStatus;

}
