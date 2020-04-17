/**
 * @FileName: QuestionService
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:32
 * Description: 提问业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Question;

public interface QuestionService {

    /**
     * 保存，添加
     * @param question
     */
    void save(Question question);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Question getById(Integer id);

    /**
     * 更新
     * @param question
     */
    void update(Question question);

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
    Page<Question> getByPage(Page<Question> page);

    /**
     * 问题 已解决
     * @param id
     */
    void isSolve(Integer id);

    /**
     * 问题 未解决
     * @param id
     */
    void noSolve(Integer id);
}
