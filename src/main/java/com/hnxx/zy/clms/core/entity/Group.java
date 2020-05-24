package com.hnxx.zy.clms.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Serializable {

    private int groupId;
    private String groupName;
    private int groupStates;
}
