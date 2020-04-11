/**
 * @FileName: Answer
 * @Author: code-fusheng
 * @Date: 2020/4/11 19:10
 * Description: 答疑(答复)
 */
package com.hnxx.zy.clms.core.entity;

import lombok.Data;

@Data
public class Answer {

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
     * 答复时间
     */
    private String answerTime;

    /**
     * 答复人
     */
    private String answerAuthor;

    /**
     * 答复标记
     * 0 : 默认 --> 一般的答复
     * 1 : 采纳 --> 指当前问题的优质答复
     * 2 : 错误 --> 标记错误的答复(权限)
     */
    private Integer mark;

}
