/**
 * @FileName: CommentServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/3/24 14:44
 * Description: 评论业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.exception.ClmsException;
import com.hnxx.zy.clms.core.entity.Comment;
import com.hnxx.zy.clms.core.mapper.CommentMapper;
import com.hnxx.zy.clms.core.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 保存,添加
     *
     * @param comment
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Comment comment) {
        // 评论类型为 文章评论 父类id为空
        if (comment.getCommentType().equals(0) && comment.getPid() != null){
            throw new ClmsException("参数错误,评论失败!");
        }else if (comment.getCommentType().equals(1) && comment.getPid() == null){
            throw new ClmsException("参数错误,腈纶失败!");
        }else {
            commentMapper.save(comment);
        }
    }

    /**
     * 根据id查询单条评论
     * @param id
     * @return
     */
    @Override
    public Comment getById(Integer id) {
        return commentMapper.getById(id);
    }

    /**
     * 查询所有,层级结构
     *
     * @return
     */
    @Override
    public List<Comment> getAll(){
        List<Comment> commentList = commentMapper.getAll();
        // 遍历 getAll 返回的文章评论实体
        for (Comment comment : commentList) {
            // 拿到文章评论实体的id值
            int id = comment.getCommentId();
            List<Comment> comments = commentMapper.getCommentByPid(id);
            for (Comment comment1 : comments) {
                comment.getCommentList().add(comment1);
            }
        }
        return commentList;
    }

    /**
     * 根据id删除评论 []
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        // 先查询校验 在删除
        Comment comment = commentMapper.getById(id);
        // 获取评论的类型
        int type = comment.getCommentType();
        // 如果类型为文章评论, 则删除评论下的评论
        if(type == StateEnum.ARTICLE_COMMENT.getCode()){
            // 获取文章评论的id
            int pid = comment.getCommentId();
            // 先根据文章评论id删除 评论的评论
            commentMapper.deleteByPid(pid);
        }
        commentMapper.deleteById(id);
    }


}