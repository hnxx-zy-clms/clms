/**
 * @FileName: CollectionController
 * @Author: code-fusheng
 * @Date: 2020/3/26 13:07
 * Description: 收藏控制类
 */
package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.core.entity.*;
import com.hnxx.zy.clms.core.mapper.CollectionMapper;
import com.hnxx.zy.clms.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    /**
     * 添加保存收藏
     * @param collection
     * @return
     */
    @PostMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> save(@RequestBody Collection collection) {
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Message message = new Message();
        message.setSendUser(user.getUserName());
        message.setMessageType(StateEnum.MESSAGE_NO_READ.getCode());
        collection.setUserId(user.getUserId());
        // 获取收藏的用户id
        int uid = collection.getUserId();
        // 获取用户收藏列表
        List<Collection> collectionList = collectionMapper.getListByUserId(uid);
        // 收藏类型判断
        // 收藏类型为 文章
        if (collection.getArticleId() != null) {
            // 获取文章id
            int aid = collection.getArticleId();
            Article article = articleService.getById(aid);
            for (Collection collectionItem : collectionList) {
                if(collectionItem.getArticleId() == null) {
                    continue;
                } else {
                    if(uid == collectionItem.getUserId() && aid == collectionItem.getArticleId()) {
                        return new Result<>("文章已经收藏!(重复收藏)");
                    }
                }
            }
            message.setReceiveUser(article.getArticleAuthor());
            message.setMessageContent(aid);
            message.setMessageDesc(article.getArticleTitle());
            message.setMessageType(StateEnum.ARTICLE_COLLECTION_MESSAGE.getCode());
            collectionMapper.collectionArticle(aid);
        }
        else if (collection.getQuestionId() != null) {
            // 获取问题id
            int qid = collection.getQuestionId();
            Question question = questionService.getById(qid);
            for (Collection collectionItem : collectionList) {
                if(collectionItem.getQuestionId() == null) {
                    continue;
                } else {
                    if(uid == collectionItem.getUserId() && qid == collectionItem.getQuestionId()) {
                        return new Result<>("问题已经收藏!(重复收藏)");
                    }
                }
            }
            message.setReceiveUser(question.getQuestionAuthor());
            message.setMessageContent(qid);
            message.setMessageDesc(question.getQuestionDescription());
            message.setMessageType(StateEnum.QUESTION_COLLECTION_MESSAGE.getCode());
            collectionMapper.collectionQuestion(qid);
        }
        else if(collection.getVideoId() != null) {
            // 获取视频id
            int vid = collection.getVideoId();
            for (Collection collectionItem : collectionList) {
                if (collectionItem.getVideoId() == null) {
                    continue;
                } else {
                    if (uid == collectionItem.getUserId() && vid == collectionItem.getVideoId()) {
                        return new Result<>("视频收藏成功!(重复收藏)");
                    }
                }
            }
            collectionMapper.collectionVideo(vid);
        }
        // 保存消息
        messageService.save(message);
        collectionService.save(collection);
        return new Result<>("收藏成功!");
    }

    @PostMapping("/getCollection")
    public Result<Integer> getCollection(@RequestBody Collection collection) {
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer uid = user.getUserId();
        int count = 0;
        // 获取查询对象的id
        // 走文章、提问、视频 收藏查询
        if(collection.getArticleId() != null) {
            int aid = collection.getArticleId();
            count = collectionService.getCollectionCountForArticle(uid,aid);
        }else if(collection.getQuestionId() != null) {
            int qid = collection.getQuestionId();
            count = collectionService.getCollectionCountForQuestion(uid, qid);
        }else if(collection.getVideoId() != null) {
            int vid = collection.getVideoId();
            count = collectionService.getCollectionCountForVideo(uid, vid);
        }else {
            return new Result<>("参数错误");
        }
        return new Result<>(count);
    }


    /**
     * 根据id删除 取消收藏
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Collection> delete(@PathVariable("id") Integer id){
        collectionService.deleteById(id);
        return new Result<>("删除成功!");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public Result<Collection> get(@PathVariable("id") Integer id){
        Collection collection = collectionService.getById(id);
        return new Result<>(collection);
    }

    /**
     * 根据用户id查询收藏列表
     * @return
     */
    @GetMapping("/getList")
    public Result<List<Collection>> getList(){
        User user =userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        int id = user.getUserId();
        List<Collection> collectionList = collectionService.getListByUserId(id);
        return new Result<>(collectionList);
    }

    /**
     * 根据登录用户id查询当前文章收藏信息
     * @return
     */
    @GetMapping("/getCollection/{articleId}")
    public Result<Integer> getCollection(@PathVariable("articleId") Integer aid){
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer uid = user.getUserId();
        int count = collectionService.getCollectionCount(uid,aid);
        return new Result<>(count);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Collection>> getByPage(@RequestBody Page<Collection> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            // 响应时间，请求时间 排序
            String[] sortColumns = {"collection_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = collectionService.getByPage(page);
        return new Result<>(page);
    }


}
