package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: chenii
 * @date: 2020/4/10 12:58
 * @version: 1.0
 * @desc:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NoticeUser implements Serializable {


    private static final long serialVersionUID = -8038090993155436763L;

    /**
     * ID
     */
    private Integer Id;

    /**
     * 通知id
     */
    private Integer noticeId;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 是否已阅：1是0否
     */
    @JsonProperty("ifRead")
    private boolean ifRead;

    /**
     * 创建时间
     */

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date readTime;


}
