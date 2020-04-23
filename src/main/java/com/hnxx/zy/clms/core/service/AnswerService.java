/**
 * @FileName: AnswerService
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:33
 * Description: 答复业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Answer;
import com.hnxx.zy.clms.core.entity.Question;

public interface AnswerService {

    /**
     * 保存，添加
     * @param answer
     */
    void save(Answer answer);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Answer getById(Integer id);

    /**
     * 更新
     * @param answer
     */
    void update(Answer answer);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Answer> getByPage(Page<Answer> page);

    /**
     * 已采纳
     * @param id
     */
    void isAdopt(Integer id);

    /**
     * 未采纳
     * @param id
     */
    void noAdopt(Integer id);
}
