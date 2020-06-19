package com.hnxx.zy.clms.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 南街北巷
 * @data 2020/4/10 18:59
 */
@Data
public class Login implements Serializable {
    private Integer user_id;
    private String user_name;
    private String user_password;
    private String user_college_id;
    private String user_classes_id;
    private String user_group_id;
    private String user_icon;
    private String user_description;
    private long created_time;
    private long update_time;
    private int user_position_id;
    private int user_status;
}
