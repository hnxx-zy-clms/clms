package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: chenii
 * @date: 2020/3/19 12:46
 * @version: 1.0
 * @desc:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Registration implements Serializable {

    private static final long serialVersionUID = 1491352640788208709L;

    /**
     * 签到id
     */
    private Integer signId;

    /**
     * 签到人id
     */
    private Integer userId;

    /**
     * 签到时间
     */
    private Date signTime;

    /**
     * 签到的课程-划分为5个课时，1-5
     */
    private String signClass;

    /**
     * 签到人姓名
     */
    private String userName;


}
