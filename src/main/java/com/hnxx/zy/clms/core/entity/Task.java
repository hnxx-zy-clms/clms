package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 发布时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pushedTime;

    /**
     * 是否启用--是1否0
     */
    private Boolean isEnabled;


    /**
     * 是否删除
     */
    private Boolean isDeleted;

    /**
     * 创建人姓名
     */
    private String name;

    /**
     * 完成人数
     */
    private Integer numDid;

    /**
     * 任务回复内容
     */
    private TaskUser taskUser;

    /**
     * @Description: 截止时间
     * @Param:
     * @return:
     * @Author: CHENLH
     * @Date: 2020-05-19 21:26:23
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deadLine;

    /**
     * @Description: 文件名
     * @Param:
     * @return:
     * @Author: CHENLH
     * @Date: 2020-05-19 21:26:46
     */
    private String fileName;

    /**
     * @Description: 文件地址
     * @Param:
     * @return:
     * @Author: CHENLH
     * @Date: 2020-05-19 21:26:54
     */
    private String fileUrl;



}
