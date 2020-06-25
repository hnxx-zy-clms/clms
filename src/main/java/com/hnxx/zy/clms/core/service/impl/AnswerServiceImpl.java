/**
 * @FileName: AnswerServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:34
 * Description: 答复业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Answer;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Question;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.mapper.AnswerMapper;
import com.hnxx.zy.clms.core.mapper.QuestionMapper;
import com.hnxx.zy.clms.core.service.AnswerService;
import com.hnxx.zy.clms.core.service.GoodService;
import com.hnxx.zy.clms.core.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
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

    //面向对象操作
    @Autowired
    @Qualifier("restHighLevelClient")  //如定义的名称与配置文件一直则不需要这个
    private RestHighLevelClient client;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Answer answer) {
        // 保存数据
        answerMapper.save(answer);
        int qid = answer.getQuestionId();
        int aCount = questionMapper.getAnswerCount(qid);
        // 更新问题的答复数
        questionMapper.updateAnswerCount(qid, aCount);
        // 添加Elasticsearch数据
        Answer answerDoc = answerMapper.getById(answer.getAnswerId());
        IndexRequest request = new IndexRequest("clms_answer_index");
        request.id(answer.getAnswerId().toString());
        request.timeout("5s");
        request.source(JSON.toJSONString(answerDoc), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Answer getById(Integer id) {
        Answer answer = answerMapper.getById(id);
        return answer;
    }

    @Override
    public void update(Answer answer) {
        answerMapper.update(answer);
        // 更新Elasticsearch数据
        Answer answerDoc = answerMapper.getById(answer.getAnswerId());
        UpdateRequest request = new UpdateRequest("clms_answer_index", answerDoc.getAnswerId().toString());
        request.timeout("5s");
        request.doc(JSON.toJSONString(answerDoc), XContentType.JSON);
        try {
            client.update(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        answerMapper.deletedById(id);
        DeleteRequest request = new DeleteRequest("clms_answer_index", id.toString());
        request.timeout("5s");
        try {
            client.delete(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
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

    @Override
    public List<Answer> getList() {
        return answerMapper.getList();
    }
}
