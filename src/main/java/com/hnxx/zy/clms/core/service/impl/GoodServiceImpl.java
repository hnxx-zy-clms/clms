/**
 * @FileName: GoodServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:32
 * Description: 点赞业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.exception.ClmsException;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.WebSocketServer;
import com.hnxx.zy.clms.core.entity.*;
import com.hnxx.zy.clms.core.mapper.GoodMapper;
import com.hnxx.zy.clms.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private MessageService messageService;


    /**
     * 点赞操作
     *
     * @param good
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doGood(Good good) {
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Message message = new Message();
        message.setSendUser(user.getUserName());
        message.setMessageState(StateEnum.MESSAGE_NO_READ.getCode());
        int goodType = good.getGoodType();
        switch (goodType) {
            case 0:
                int aid = good.getArticleId();
                Article article = articleService.getById(aid);
                goodMapper.goodArticle(aid);
                message.setReceiveUser(article.getArticleAuthor());
                message.setMessageContent(aid);
                message.setMessageDesc(article.getArticleTitle());
                message.setMessageType(StateEnum.ARTICLE_GOOD_MESSAGE.getCode());
                break;
            case 1:
                int cid = good.getCommentId();
                Comment comment = commentService.getById(cid);
                goodMapper.goodComment(cid);
                message.setReceiveUser(comment.getCommentUser());
                message.setMessageContent(comment.getCommentArticle());
                message.setMessageDesc(comment.getCommentContent());
                message.setMessageType(StateEnum.COMMENT_GOOD_MESSAGE.getCode());
                break;
            case 2:
                int qid = good.getQuestionId();
                Question question = questionService.getById(qid);
                goodMapper.goodQuestion(qid);
                message.setReceiveUser(question.getQuestionAuthor());
                message.setMessageContent(qid);
                message.setMessageDesc(question.getQuestionDescription());
                message.setMessageType(StateEnum.QUESTION_GOOD_MESSAGE.getCode());
                break;
            case 3:
                int sid = good.getAnswerId();
                Answer answer = answerService.getById(sid);
                goodMapper.goodAnswer(sid);
                message.setReceiveUser(answer.getAnswerAuthor());
                message.setMessageContent(answer.getQuestionId());
                message.setMessageDesc(answer.getAnswerContent());
                message.setMessageType(StateEnum.ANSWER_GOOD_MESSAGE.getCode());
                break;
            case 4:
                int vid = good.getVideoId();
                Video video = videoService.getVideoById(vid);
                goodMapper.goodVideo(vid);
                break;
            default: return;
        }
        // 保存消息
        messageService.save(message);
        try {
            WebSocketServer.sendInfo(JSON.toJSONString(message));
        }catch (Exception e){
            e.printStackTrace();
        }
        goodMapper.save(good);
    }

    /**
     * 根据id获取用户点赞集合
     * @param id
     * @return
     */
    @Override
    public List<Good> getListByUserId(Integer id) {
        List<Good> goodList = goodMapper.getListByUserId(id);
        return goodList;
    }

    /**
     * 用户文章是否点赞
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public int getGoodCountForArticle(Integer uid, Integer aid) {
        int count = 0;
        List<Good> goods = goodMapper.getGoodCountForArticle(uid, aid);
        count = (goods.size() == 0) ? 0 : 1;
        return count;
    }

    /**
     * 用户评论是否点赞
     * @param cid
     * @param uid
     * @return
     */
    @Override
    public int getGoodCountForComment(Integer uid, Integer cid) {
        int count = 0;
        List<Good> goods = goodMapper.getGoodCountForComment(uid, cid);
        count = (goods.size() == 0) ? 0 : 1;
        return count;
    }

    /**
     * 用户提问是否点赞
     * @param qid
     * @param uid
     * @return
     */
    @Override
    public int getGoodCountForQuestion(Integer uid, Integer qid) {
        int count = 0;
        List<Good> goods = goodMapper.getGoodCountForQuestion(uid, qid);
        count = (goods.size() == 0) ? 0 : 1;
        return count;
    }

    /**
     * 用户答复是否点赞
     * @param sid
     * @param uid
     * @return
     */
    @Override
    public int getGoodCountForAnswer(Integer uid, Integer sid) {
        int count = 0;
        List<Good> goods = goodMapper.getGoodCountForAnswer(uid, sid);
        count = (goods.size() == 0) ? 0 : 1;
        return count;
    }

    /**
     * 用户视频是否点赞
     * @param vid
     * @param uid
     * @return
     */
    @Override
    public int getGoodCountForVideo(Integer uid, Integer vid) {
        int count = 0;
        List<Good> goods = goodMapper.getGoodCountForVideo(uid, vid);
        if(goods.size() == 0) {
            count = 0;
        }else {
            count = 1;
        }
        return count;
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<Good> getByPage(Page<Good> page) {
        // 查询数据
        List<Good> goodList = goodMapper.getByPage(page);
        page.setList(goodList);
        // 统计总数
        int totalCount = goodMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 取消点赞
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        goodMapper.deleteById(id);
    }



}
