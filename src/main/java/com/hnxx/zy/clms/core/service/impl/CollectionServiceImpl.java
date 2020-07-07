/**
 * @FileName: ConnectionServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/3/26 11:11
 * Description: 收藏业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.WebSocketServer;
import com.hnxx.zy.clms.core.entity.*;
import com.hnxx.zy.clms.core.mapper.ArticleMapper;
import com.hnxx.zy.clms.core.mapper.CollectionMapper;
import com.hnxx.zy.clms.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

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
     * 保存添加收藏
     * @param collection
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Collection collection) {
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Message message = new Message();
        message.setSendUser(user.getUserName());
        message.setMessageState(StateEnum.MESSAGE_NO_READ.getCode());
        int collectionType = collection.getCollectionType();
        switch (collectionType) {
            case 1:
                int aid = collection.getArticleId();
                Article article = articleService.getById(aid);
                collectionMapper.collectionArticle(aid);
                message.setReceiveUser(article.getArticleAuthor());
                message.setMessageContent(aid);
                message.setMessageDesc(article.getArticleTitle());
                message.setMessageType(StateEnum.ARTICLE_COLLECTION_MESSAGE.getCode());
                break;
            case 2:
                int qid = collection.getQuestionId();
                Question question = questionService.getById(qid);
                collectionMapper.collectionQuestion(qid);
                message.setReceiveUser(question.getQuestionAuthor());
                message.setMessageContent(qid);
                message.setMessageDesc(question.getQuestionDescription());
                message.setMessageType(StateEnum.QUESTION_COLLECTION_MESSAGE.getCode());
                break;
            case 3:
                int vid = collection.getVideoId();
                collectionMapper.collectionVideo(vid);
                break;
            default:return;
        }
        // 保存消息
        messageService.save(message);
        try {
            WebSocketServer.sendInfo(JSON.toJSONString(message));
        }catch (Exception e){
            e.printStackTrace();
        }
        collectionMapper.save(collection);
    }

    /**
     * 用户文章是否收藏
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public int getCollectionCountForArticle(Integer uid, Integer aid) {
        int count = 0;
        List<Collection> collections = collectionMapper.getCollectionCountForArticle(uid, aid);
        count = (collections.size() == 0) ? 0 : 1;
        return count;
    }

    /**
     * 用户提问是否收藏
     * @param qid
     * @param uid
     * @return
     */
    @Override
    public int getCollectionCountForQuestion(Integer uid, Integer qid) {
        int count = 0;
        List<Collection> collections = collectionMapper.getCollectionCountForQuestion(uid, qid);
        count = (collections.size() == 0) ? 0 : 1;
        return count;
    }

    /**
     * 用户视频是否收藏
     * @param vid
     * @param uid
     * @return
     */
    @Override
    public int getCollectionCountForVideo(Integer uid, Integer vid) {
        int count = 0;
        List<Collection> collections = collectionMapper.getCollectionCountForVideo(uid, vid);
        count = (collections.size() == 0) ? 0 : 1;
        return count;
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        collectionMapper.delete(id);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Collection getById(Integer id) {
        return collectionMapper.getById(id);
    }

    /**
     * 用户文章收藏
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public int getCollectionCount(Integer uid, Integer aid) {
        int count;
        List<Collection> collections = collectionMapper.getCollectionCount(uid, aid);
        count = (collections.size() == 0) ? 0 : 1;
        return count;
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    public Page<Collection> getByPage(Page<Collection> page) {
        // 查询数据
        List<Collection> collectionList = collectionMapper.getByPage(page);
        page.setList(collectionList);
        // 查询总数
        int totalCount = collectionMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据用户id查询收藏列表
     * @param id
     * @return
     */
    @Override
    public List<Collection> getListByUserId(Integer id) {
        List<Collection> collectionList = collectionMapper.getListByUserId(id);
        return collectionList;
    }
}
