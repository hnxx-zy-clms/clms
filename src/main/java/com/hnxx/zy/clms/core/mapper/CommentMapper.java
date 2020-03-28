/**
 * @FileName: ArticleCommentMapper
 * @Author: code-fusheng
 * @Date: 2020/3/24 14:46
 * Description: 评论mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Comment;
import com.hnxx.zy.clms.core.service.CommentService;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    /**
     * 保存,添加
     * @param comment
     */
    @Insert("Insert into cl_comment(comment_content, comment_user, comment_article, comment_type, pid)" +
            "values (#{commentContent}, #{commentUser}, #{commentArticle}, #{commentType}, #{pid})")
    void save(Comment comment);

    /**
     * 根据id查询 单条评论
     * @param id
     * @return
     */
    @Select("select * from cl_comment where comment_id = #{id} and is_deleted = 0")
    Comment getById(Integer id);


    /**
     * 根据文章id获取评论列表
     * @param aid
     * @return
     */
    @Select("select * from cl_comment where comment_type = 0 and comment_article = #{aid} and is_deleted = 0")
    List<Comment> getCommentByAid(Integer aid);

    /**
     * 获取所有文章评论
     * @return
     */
    @Select("select * from cl_comment where comment_type = 0 and is_deleted = 0")
    List<Comment> getAll();

    /**
     * 通过父级id获取所有评论下的评论
     * @param pid
     * @return
     */
    @Select("select * from cl_comment where comment_type = 1 and pid = #{pid}")
    List<Comment> getCommentByPid(Integer pid);


    /**
     * 根据id删除
     * @param id
     */
    @Delete("delete from cl_comment where comment_id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据文章评论的id删除评论的评论
     * @param pid
     */
    @Delete("delete from cl_comment where pid = #{pid}")
    void deleteByPid(int pid);

    /**
     * 根据aid统计总数 文章评论总数
     * @param aid
     * @return
     */
    @Select("select count(*) from cl_comment where comment_article = #{aid}")
    int getCountByAid(int aid);

    /**
     * 根据pid统计总数 评论的评论总数
     * @param pid
     * @return
     */
    @Select("select count(*) from cl_comment where pid = #{pid}")
    int getCountByCid(int pid);

    /**
     * 根据文章id更新文章的评论量
     * @param cCount
     * @param aid
     */
    @Update("update cl_article set article_comment = #{cCount} where article_id = #{aid}")
    void updateACommentCount(int cCount, int aid);

    /**
     * 根据评论id更新评论的评论量
     * @param cCount
     * @param cid
     */
    @Update("update cl_comment set comment_count = #{cCount} where comment_id = #{cid}")
    void updateCCommentCount(int cCount, int cid);
}
