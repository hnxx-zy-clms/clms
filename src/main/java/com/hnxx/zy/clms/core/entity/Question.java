/**
 * @FileName: Question
 * @Author: code-fusheng
 * @Date: 2020/4/11 19:10
 * Description: 答疑(问题)
 */
package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question implements Serializable {

    /**
     * 问题id
     */
    private Integer questionId;

    /**
     * 问题描述
     */
    private String questionDescription;

    /**
     * 问题内容
     */
    private String questionContent;

    /**
     * 提问作者
     */
    private String questionAuthor;

    /**
     * 提问时间
     */
    private String questionTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 提问点赞数
     */
    private Integer questionGood;

    /**
     * 提问答复数
     */
    private Integer answerCount;

    /**
     * 问题标记 可以理解为状态
     * 0 : 未解决
     * 1 : 已解决
     */
    private Integer questionMark;

    /**
     * 答复列表 一对多
     */
    private List<Answer> answerList;

    /**
     * 相似问题（目前只考虑到描述中有相似内容，但是具体不知道怎么实现？）
     * 预留功能 该字段并不存储数据 直接一接口形式展开
     */
    private List<Question> sameQuestionList;

    /**
     * 推荐答复（根据问题自动提示n条已经存在的答复，相关问题的优质答案）
     * 预留功能 该字段并不存储数据 直接一接口形式展开
     */
    private List<Answer> autoAnswerList;

    /**
     * 是否删除
     * 0 : 否
     * 1 : 是
     */
    private Integer isDeleted;

    /**
     * 作者头像 vo属性
     */
    private String userIcon;

    /**
     * 提问点赞标识 仅用于前端展示，不做数据持久化
     */
    private boolean goodQuestionFlag = false;

}
