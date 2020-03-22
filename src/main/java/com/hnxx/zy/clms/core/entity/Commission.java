package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: chenii
 * @date: 2020/3/22 12:39
 * @version: 1.0
 * @desc:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Commission implements Serializable {


    private static final long serialVersionUID = -8724518310083269150L;

    /**
     * 代办id
     */
    private Integer comId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 代办内容
     */
    private String comContent;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 是否完成：1是0否
     */
    private boolean isDid;


}
