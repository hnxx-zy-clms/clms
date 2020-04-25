package com.hnxx.zy.clms.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class College implements Serializable {

    private int collegeId;

    private String collegeName;

    private int collegeStates;

}