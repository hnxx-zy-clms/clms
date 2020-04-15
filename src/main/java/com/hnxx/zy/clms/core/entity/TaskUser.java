package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: chenii
 * @date: 2020/4/13 11:19
 * @version: 1.0
 * @desc:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskUser implements Serializable {

    private static final long serialVersionUID = 2737900483046037849L;

    /**
     * ID
     */
    private Integer Id;

    /**
     * 任务ID
     */
    private Integer taskId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 是否已完成
     */
    private Boolean isDid;

    /**
     * 完成时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date didTime;


    /**
     * 回复内容
     */
    private String replyContent;
}
