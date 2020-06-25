/**
 * @FileName: QuestionServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/4/11 22:33
 * Description: 问题业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Question;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.mapper.AnswerMapper;
import com.hnxx.zy.clms.core.mapper.QuestionMapper;
import com.hnxx.zy.clms.core.service.GoodService;
import com.hnxx.zy.clms.core.service.QuestionService;
import com.hnxx.zy.clms.core.service.UserService;
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
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

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
    public void save(Question question) {
        questionMapper.save(question);
        // 添加Elasticsearch数据
        Question questionDoc = questionMapper.getById(question.getQuestionId());
        IndexRequest request = new IndexRequest("clms_question_index");
        request.id(question.getQuestionId().toString());
        request.timeout("5s");
        request.source(JSON.toJSONString(questionDoc), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Question getById(Integer id) {
        // 获取问题实体对象
        Question question = questionMapper.getById(id);
        return question;
    }

    @Override
    public List<Question> getList() {
        return questionMapper.getList();
    }

    @Override
    public void update(Question question) {
        questionMapper.update(question);
        // 更新Elasticsearch数据
        Question questionDoc = questionMapper.getById(question.getQuestionId());
        UpdateRequest request = new UpdateRequest("clms_question_index", questionDoc.getQuestionId().toString());
        request.timeout("5s");
        request.doc(JSON.toJSONString(questionDoc), XContentType.JSON);
        try {
            client.update(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        questionMapper.deletedById(id);
        DeleteRequest request = new DeleteRequest("clms_question_index", id.toString());
        request.timeout("5s");
        try {
            client.delete(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Page<Question> getByPage(Page<Question> page) {
        // 先查询数据
        List<Question> questionList = questionMapper.getByPage(page);
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        int uid = user.getUserId();
        questionList.forEach( question -> {
            int count = goodService.getGoodCountForQuestion(uid, question.getQuestionId());
            if(count == 0){
                question.setGoodQuestionFlag(false);
            }else {
                question.setGoodQuestionFlag(true);
            }
        } );
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
