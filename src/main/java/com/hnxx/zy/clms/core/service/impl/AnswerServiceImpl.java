/**
 * @FileName: AnswerServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:34
 * Description: 答复业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Answer;
import com.hnxx.zy.clms.core.entity.Question;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.mapper.AnswerMapper;
import com.hnxx.zy.clms.core.mapper.QuestionMapper;
import com.hnxx.zy.clms.core.service.AnswerService;
import com.hnxx.zy.clms.core.service.GoodService;
import com.hnxx.zy.clms.core.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodService goodService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Answer answer) {
        // 保存数据
        answerMapper.save(answer);
        int qid = answer.getQuestionId();
        int aCount = questionMapper.getAnswerCount(qid);
        // 更新问题的答复数
        questionMapper.updateAnswerCount(qid, aCount);
    }

    @Override
    public Answer getById(Integer id) {
        Answer answer = answerMapper.getById(id);
        return answer;
    }

    @Override
    public void update(Answer answer) {
        answerMapper.update(answer);
    }

    @Override
    public void deleteById(Integer id) {
        answerMapper.deletedById(id);
    }

    @Override
    public Page<Answer> getByPage(Page<Answer> page) {
        // 先查询数据
        List<Answer> answerList = answerMapper.getByPage(page);
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        int uid = user.getUserId();
        answerList.forEach( answer -> {
            int count = goodService.getGoodCountForAnswer(uid, answer.getAnswerId());
            if(count == 0){
                answer.setGoodAnswerFlag(false);
            }else {
                answer.setGoodAnswerFlag(true);
            }
        } );
        page.setList(answerList);
        // 在查询总数
        int totalCount = answerMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void isAdopt(Integer id) {
        // 设置答复状态码,已采纳
        int mark = StateEnum.IS_ADOPT_ANSWER.getCode();
        answerMapper.changeAdopt(id, mark);
    }

    @Override
    public void noAdopt(Integer id) {
        // 设置答复状态码,未采纳
        int mark = StateEnum.NO_ADOPT_ANSWER.getCode();
        answerMapper.changeAdopt(id, mark);
    }
}
