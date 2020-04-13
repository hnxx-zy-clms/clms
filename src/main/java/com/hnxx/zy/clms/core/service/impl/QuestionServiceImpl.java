/**
 * @FileName: QuestionServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:33
 * Description: 问题业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Question;
import com.hnxx.zy.clms.core.mapper.AnswerMapper;
import com.hnxx.zy.clms.core.mapper.QuestionMapper;
import com.hnxx.zy.clms.core.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;


    @Override
    public void save(Question question) {

        questionMapper.save(question);
    }

    @Override
    public Question getById(Integer id) {
        // 获取问题实体对象
        Question question = questionMapper.getById(id);
        return question;
    }

    @Override
    public void update(Question question) {
        questionMapper.update(question);
    }

    @Override
    public void deleteById(Integer id) {
        questionMapper.deletedById(id);
    }

    @Override
    public Page<Question> getByPage(Page<Question> page) {
        // 先查询数据
        List<Question> questionList = questionMapper.getByPage(page);
        page.setList(questionList);
        // 在查询总数
        int totalCount = questionMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void isSolve(Integer id) {
        // 设置问题状态码,已解答
        int mark = StateEnum.IS_SOLVE_QUESTION.getCode();
        questionMapper.changeSolve(id, mark);
    }

    @Override
    public void noSolve(Integer id) {
        // 设置问题状态码,未解答
        int mark = StateEnum.NO_SOLVE_QUESTION.getCode();
        questionMapper.changeSolve(id, mark);
        // 同时将所有相关答案的已采纳,重置为未采纳
    }
}
