package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: chenii
 * @date: 2020/3/22 18:34
 * @version: 1.0
 * @desc:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task implements Serializable {

    private static final long serialVersionUID = -8335041764799873395L;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 创建人id
     */
    private Integer createdId;

    /**
     * 任务标题
     */
    private String taskTitle;

    /**
     * 任务内容
     */
    private String taskContent;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 是否启用  是1否0
     */
    private Boolean isEnabled;

    /**
     * 是否已完成
     */
    private Boolean isDid;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 回复内容
     */
    private String replyContent;

}
