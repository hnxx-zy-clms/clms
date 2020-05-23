package com.hnxx.zy.clms.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    private Integer positionId;

    private String positionName;

    private Integer positionStatus;

}
