/**
 * @FileName: GoodController
 * @Author: code-fusheng
 * @Date: 2020/3/25 15:10
 * Description: 点赞控制类
 */
package com.hnxx.zy.clms.controller;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.enums.ResultEnum;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.StringUtils;
import com.hnxx.zy.clms.common.utils.WebSocketServer;
import com.hnxx.zy.clms.core.entity.*;
import com.hnxx.zy.clms.core.mapper.GoodMapper;
import com.hnxx.zy.clms.core.mapper.MessageMapper;
import com.hnxx.zy.clms.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodMapper goodMapper;

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

    @Autowired
    private VideoService videoService;

    /**
     * 点赞
     * @param good
     * @return
     */
    @PostMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    public Result<Object> save(@RequestBody Good good){
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Message message = new Message();
        message.setSendUser(user.getUserName());
        message.setMessageState(StateEnum.MESSAGE_NO_READ.getCode());
        good.setUserId(user.getUserId());
        // 获取用户id
        int uid = good.getUserId();
        if (good.getArticleId() != null) {
            // 设置点赞类型 文章 0
            good.setGoodType(StateEnum.ARTICLE_GOOD.getCode());
            List<Good> goodList = goodMapper.getListByUserIdWithGoodType(uid, StateEnum.ARTICLE_GOOD.getCode());
            // 获取文章id
            int aid = good.getArticleId();
            Article article = articleService.getById(aid);
            for (Good oldGood : goodList) {
                if(oldGood.getArticleId() == null){
                    continue;
                }else {
                    if (uid == oldGood.getUserId() && aid == oldGood.getArticleId()) {
                        return new Result<>("文章点赞成功!(重复点赞)");
                    }
                }
            }
            message.setReceiveUser(article.getArticleAuthor());
            message.setMessageContent(aid);
            message.setMessageDesc(article.getArticleTitle());
            message.setMessageType(StateEnum.ARTICLE_GOOD_MESSAGE.getCode());
            goodMapper.goodArticle(aid);
        } else if (good.getCommentId() != null) {
            // 设置点赞类型 评论 1
            good.setGoodType(StateEnum.COMMENT_GOOD.getCode());
            List<Good> goodList = goodMapper.getListByUserIdWithGoodType(uid, StateEnum.COMMENT_GOOD.getCode());
            // 获取评论id
            int cid = good.getCommentId();
            Comment comment = commentService.getById(cid);
            for (Good oldGood : goodList) {
                if(oldGood.getCommentId() == null){
                    continue;
                }else {
                    if (uid == oldGood.getUserId() && cid == oldGood.getCommentId()) {
                        return new Result<>("评论点赞成功!(重复点赞)");
                    }
                }
            }
            message.setReceiveUser(comment.getCommentUser());
            message.setMessageContent(comment.getCommentArticle());
            message.setMessageDesc(comment.getCommentContent());
            message.setMessageType(StateEnum.COMMENT_GOOD_MESSAGE.getCode());
            goodMapper.goodComment(cid);
        } else if (good.getQuestionId() != null) {
            // 设置点赞类型 提问 2
            good.setGoodType(StateEnum.QUESTION_GOOD.getCode());
            List<Good> goodList = goodMapper.getListByUserIdWithGoodType(uid, StateEnum.QUESTION_GOOD.getCode());
            // 获取提问id
            int qid = good.getQuestionId();
            Question question = questionService.getById(qid);
            for (Good oldGood : goodList) {
                if(oldGood.getQuestionId() == null){
                    continue;
                }else {
                    if (uid == oldGood.getUserId() && qid == oldGood.getQuestionId()) {
                        return new Result<>("提问点赞成功!(重复点赞)");
                    }
                }
            }
            message.setReceiveUser(question.getQuestionAuthor());
            message.setMessageContent(qid);
            message.setMessageDesc(question.getQuestionDescription());
            message.setMessageType(StateEnum.QUESTION_GOOD_MESSAGE.getCode());
            goodMapper.goodQuestion(qid);
        } else if (good.getAnswerId() != null) {
            // 设置点赞类型 答复 3
            good.setGoodType(StateEnum.ANSWER_GOOD.getCode());
            List<Good> goodList = goodMapper.getListByUserIdWithGoodType(uid, StateEnum.ANSWER_GOOD.getCode());
            // 获取答复id
            int sid = good.getAnswerId();
            Answer answer = answerService.getById(sid);
            for (Good oldGood : goodList) {
                if(oldGood.getAnswerId() == null){
                    continue;
                }else {
                    if (uid == oldGood.getUserId() && sid == oldGood.getAnswerId()) {
                        return new Result<>("答复点赞成功!(重复点赞)");
                    }
                }
            }
            message.setReceiveUser(answer.getAnswerAuthor());
            message.setMessageContent(answer.getQuestionId());
            message.setMessageDesc(answer.getAnswerContent());
            message.setMessageType(StateEnum.ANSWER_GOOD_MESSAGE.getCode());
            goodMapper.goodAnswer(sid);
        } else if (good.getVideoId() != null) {
            // 设置点赞类型 视频 4
            good.setGoodType(StateEnum.VIDEO_GOOD.getCode());
            List<Good> goodList = goodMapper.getListByUserIdWithGoodType(uid, StateEnum.VIDEO_GOOD.getCode());
            // 获取视频id
            int vid = good.getVideoId();
            for (Good oldGood : goodList) {
                if(oldGood.getVideoId() == null){
                    continue;
                }else {
                    if (uid == oldGood.getUserId() && vid == oldGood.getVideoId()) {
                        return new Result<>("视频点赞成功!(重复点赞)");
                    }
                }
            }
            goodMapper.goodVideo(vid);
        }
        // 保存消息
        messageService.save(message);
        try {
            WebSocketServer.sendInfo(JSON.toJSONString(message));
        }catch (Exception e){
            e.printStackTrace();
        }
        // 保存点赞信息
        goodService.doGood(good);
        return new Result<>("点赞成功!");
    }

    /**
     * 根据用户id查询点赞信息
     * @return
     */
    @GetMapping("/getList")
    public Result<List<Good>> getList(){
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer id = user.getUserId();
        List<Good> goodList = goodService.getListByUserId(id);
        return new Result<>(goodList);
    }

    /**
     * 根据登录用户id查询当前目标点赞信息
     * @return
     */
    @PostMapping("/getGood")
    public Result<Integer> getGood(@RequestBody Good good){
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer uid = user.getUserId();
        int count = 0;
        // 获取查询对象的id
        // 走文章、评论、提问、答复、视频 点赞查询
        if(good.getArticleId() != null) {
            int aid = good.getArticleId();
            count = goodService.getGoodCountForArticle(uid,aid);
        }else if(good.getCommentId() != null) {
            int cid = good.getCommentId();
            count = goodService.getGoodCountForComment(uid,cid);
        }else if(good.getQuestionId() != null) {
            int qid = good.getQuestionId();
            count = goodService.getGoodCountForQuestion(uid, qid);
        }else if(good.getAnswerId() != null) {
            int sid = good.getAnswerId();
            count = goodService.getGoodCountForAnswer(uid, sid);
        }else if(good.getVideoId() != null) {
            int vid = good.getVideoId();
            count = goodService.getGoodCountForVideo(uid, vid);
        }else {
            return new Result<>("参数错误");
        }
        return new Result<>(count);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @PostMapping("/getByPage")
    public Result<Page<Good>> getByPage(@RequestBody Page<Good> page){
        // 获取排序方式  page对象中 封装了 sortColumn 排序列
        String sortColumn = page.getSortColumn();
        // 驼峰转下划线
        String newSortColumn = StringUtils.upperCharToUnderLine(sortColumn);
        // 下划线的 排序列
        page.setSortColumn(newSortColumn);
        // 判断排序列不为空
        if(StringUtils.isNotBlank(sortColumn)){
            String[] sortColumns = {"good_time"};
            // Arrays.asList() 方法使用
            // 1. 该方法是将数组转换成list。 Json 数据格式中的 排序列为数组形式，此处需要转换成 List数据形式
            // 2. 该方法不适用于剧本数据类型（byte,short,int,long,float,double,boolean）
            // 3. 不支持add和remove方法
            List<String> sortList = Arrays.asList(sortColumns);
            if(!sortList.contains(newSortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(),"参数错误！");
            }
        }
        page = goodService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 根据id删除 取消点赞
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result<Good> delete(@PathVariable("id") Integer id){
        goodService.deleteById(id);
        return new Result<>("点赞取消!");
    }



}
