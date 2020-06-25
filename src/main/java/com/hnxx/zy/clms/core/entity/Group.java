package com.hnxx.zy.clms.core.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Serializable {

    private Integer groupId;

    @Excel(name = "组名称", orderNum = "1")
    private String groupName;

    private Integer groupStates;
}
