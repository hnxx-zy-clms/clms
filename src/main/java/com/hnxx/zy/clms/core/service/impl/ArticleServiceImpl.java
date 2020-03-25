/**
 * @FileName: ArticleServcieImpl
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:17
 * Description: 文章业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.exception.ClmsException;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Comment;
import com.hnxx.zy.clms.core.mapper.ArticleMapper;
import com.hnxx.zy.clms.core.mapper.CommentMapper;
import com.hnxx.zy.clms.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 保存
     * @param article
     */
    @Override
    public void save(Article article) {
        articleMapper.save(article);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Article getById(Integer id) {
        // 获取文章实体
        Article article = articleMapper.getById(id);
        // 给文章实体的评论总数赋值
        article.setArticleComment(commentMapper.getCountByAid(id));
        return article;
    }

    /**
     * 更新
     * @param article
     */
    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Article> getByPage(Page<Article> page) {
        // 先查询数据
        List<Article> articleList = articleMapper.getByPage(page);
        page.setList(articleList);
        // 再查询总数
        int totalCount = articleMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据id启用
     * @param id
     */
    @Override
    public void enableById(Integer id) {
        Article article = articleMapper.getById(id);
        article.setIsEnabled(StateEnum.ENABLED.getCode());
        articleMapper.updateEnable(article);
    }

    /**
     * 根据id弃用
     * @param id
     */
    @Override
    public void disableById(Integer id) {
        Article article = articleMapper.getById(id);
        article.setIsEnabled(StateEnum.NOT_ENABLE.getCode());
        articleMapper.updateEnable(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article readById(Integer id) {
        // 获取文章实体 根据id
        Article article = articleMapper.getById(id);
        // read +1
        int readCount = article.getArticleRead() + 1;
        article.setArticleRead(readCount);
        // 获取文章评论量
        int commentCount1 = commentMapper.getCountByAid(id);
        article.setArticleComment(commentCount1);
        // 获取文章下的所有文章评论
        List<Comment> commentList = commentMapper.getCommentByAid(id);
        for(Comment comment1 : commentList){
            // 获取文章评论的id 这个id是评论的评论的pid
            int pid = comment1.getCommentId();
            // 根据 pid 获取评论的评论量
            int commentCount2 = commentMapper.getCountByCid(pid);
            // 设置评论的评论量
            comment1.setCommentCount(commentCount2);
            // 通过文章的评论id获取评论的评论list
            List<Comment> comments = commentMapper.getCommentByPid(pid);
            // 遍历评论下的评论
            for (Comment comment2 : comments) {
                // 将评论下的评论添加到评论的评论集合
                comment1.getCommentList().add(comment2);
            }
            // 将文章的评论添加到文章的评论列表
            article.getCommentList().add(comment1);
        }
        return article;
    }
}
