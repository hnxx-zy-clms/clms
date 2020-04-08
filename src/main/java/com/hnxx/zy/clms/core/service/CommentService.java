/**
 * @FileName: CommentService
 * @Author: code-fusheng
 * @Date: 2020/3/24 14:44
 * Description: 评论业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 保存 添加
     * @param comment
     */
    void save(Comment comment);

    /**
     * 根据id查询单条评论
     * @param id
     * @return
     */
    Comment getById(Integer id);

    /**
     * 查询所有评论 (后台 层级结构)
     * @return
     */
    List<Comment> getAll();

    /**
     * 根据id删除评论
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询文章评论列表
     * @param id
     * @return
     */
    List<Comment> getListById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Comment> getByPage(Page<Comment> page);
}
