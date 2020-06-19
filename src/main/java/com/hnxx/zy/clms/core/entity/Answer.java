/**
 * @FileName: Answer
 * @Author: code-fusheng
 * @Date: 2020/4/11 19:10
 * Description: 答疑(答复)
 */
package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer implements Serializable {

    /**
     * 答复id
     */
    private Integer answerId;

    /**
     * 问题id
     */
    private Integer questionId;

    /**
     * 答复内容
     */
    private String answerContent;

    /**
     * 答复点赞量
     */
    private Integer answerGood;

    /**
     * 答复时间
     */

    private String answerTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 答复人
     */
    private String answerAuthor;

    /**
     * 答复标记
     * 0 : 已采纳
     * 1 : 未采纳
     */
    private Integer answerMark;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 作者头像 vo属性
     */
    private String userIcon;

    /**
     * 答复点赞标识 仅用于前端展示，不做数据持久化
     */
    private boolean goodAnswerFlag = false;
}
