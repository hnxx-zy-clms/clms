/**
 * @FileName: GoodMapper
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:33
 * Description: 点赞mapper 数据访问层
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Answer;
import com.hnxx.zy.clms.core.entity.Comment;
import com.hnxx.zy.clms.core.entity.Good;
import com.hnxx.zy.clms.core.entity.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Repository
public interface GoodMapper {

    /**
     * 文章 点赞 good +1
     * @param aid
     */
    @Update("update cl_article set article_good = article_good + 1 where article_id = #{aid}")
    void goodArticle(int aid);

    /**
     * 评论 点赞 good +1
     * @param cid
     */
    @Update("update cl_comment set comment_good = comment_good + 1 where comment_id = #{cid}")
    void goodComment(int cid);

    /**
     * 问题 点赞 good +1
     * @param qid
     */
    @Update("update cl_question set question_good = question_good + 1 where question_id = #{qid}")
    void goodQuestion(int qid);

    /**
     * 答复 点赞 good +1
     * @param sid
     */
    @Update("update cl_answer set answer_good = answer_good + 1 where answer_id = #{sid}")
    void goodAnswer(int sid);

    /**
     * 视频 点赞 good +1
     * @param vid
     */
    @Update("update cl_video set video_good = video_good + 1 where video_id = #{vid}")
    void goodVideo(int vid);

    /**
     * 保存点赞
     * @param good
     */
    @Insert("insert into cl_good(user_id, article_id, comment_id, question_id, answer_id, video_id, good_type) " +
            "values (#{userId}, #{articleId}, #{commentId}, #{questionId}, #{answerId}, #{videoId}, #{goodType})")
    void save(Good good);

    /**
     * 根据用户id查询点赞信息集合
     * @param id
     * @return
     */
    @Select("select * from cl_good where user_id = #{id}")
    List<Good> getListByUserId(Integer id);

    /**
     * 根据用户id和点赞类型查询点赞列表
     * @param uid
     * @param tid
     * @return
     */
    @Select("select * from cl_good where user_id = #{uid} and good_type = #{tid}")
    List<Good> getListByUserIdWithGoodType(Integer uid, Integer tid);

    /**
     * 根据用户id和文章id查询文章点赞信息集合
     * @param uid
     * @param aid
     * @return
     */
    @Select("select * from cl_good where user_id = #{userId} and article_id = #{articleId}")
    List<Good> getGoodCountForArticle(@Param("userId") Integer uid, @Param("articleId") Integer aid);

    /**
     * 根据用户id和评论id查询评论点赞信息集合
     * @param uid
     * @param cid
     * @return
     */
    @Select("select * from cl_good where user_id = #{userId} and comment_id = #{commentId}")
    List<Good> getGoodCountForComment(@Param("userId") Integer uid, @Param("commentId") Integer cid);

    /**
     * 根据用户id和提问id查询提问点赞信息集合
     * @param uid
     * @param qid
     * @return
     */
    @Select("select * from cl_good where user_id = #{userId} and question_id = #{questionId}")
    List<Good> getGoodCountForQuestion(@Param("userId") Integer uid, @Param("questionId") Integer qid);

    /**
     * 根据用户id和答复id查询答复点赞信息集合
     * @param uid
     * @param sid
     * @return
     */
    @Select("select * from cl_good where user_id = #{userId} and answer_id = #{answerId}")
    List<Good> getGoodCountForAnswer(@Param("userId") Integer uid, @Param("answerId") Integer sid);

    /**
     * 根据用户id和视频id查询视频点赞信息集合
     * @param uid
     * @param vid
     * @return
     */
    @Select("select * from cl_good where user_id = #{userId} and video_id = #{videoId}")
    List<Good> getGoodCountForVideo(@Param("userId") Integer uid, @Param("videoId") Integer vid);

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select * from cl_good\n" +
            "        where 1=1\n" +
            "        <if test=\"params.userId!=null\">\n" +
            "            and user_id = #{params.userId}\n" +
            "        </if>\n" +
            "        <if test=\"params.articleId!=null\">\n" +
            "            and article_id = #{params.articleId}\n" +
            "        </if>\n" +
            "        <if test=\"params.commentId!=null\">\n" +
            "            and comment_id = #{params.commentId}\n" +
            "        </if>\n" +
            "        <if test=\"params.questionId!=null\">\n" +
            "            and question_id = #{params.questionId}\n" +
            "        </if>\n" +
            "        <if test=\"params.answerId!=null\">\n" +
            "            and answer_id = #{params.answerId}\n" +
            "        </if>\n" +
            "        <if test=\"params.videoId!=null\">\n" +
            "            and video_id = #{params.videoId}\n" +
            "        </if>\n" +
            "        <if test=\"params.goodType!=null\">\n" +
            "            and good_type = #{params.goodType}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Good> getByPage(Page<Good> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select count(*) from cl_good\n" +
            "        where 1=1\n" +
            "        <if test=\"params.userId!=null\">\n" +
            "            and user_id = #{params.userId}\n" +
            "        </if>\n" +
            "        <if test=\"params.articleId!=null\">\n" +
            "            and article_id = #{params.articleId}\n" +
            "        </if>\n" +
            "        <if test=\"params.commentId!=null\">\n" +
            "            and comment_id = #{params.commentId}\n" +
            "        </if>\n" +
            "        <if test=\"params.questionId!=null\">\n" +
            "            and question_id = #{params.questionId}\n" +
            "        </if>\n" +
            "        <if test=\"params.answerId!=null\">\n" +
            "            and answer_id = #{params.answerId}\n" +
            "        </if>\n" +
            "        <if test=\"params.videoId!=null\">\n" +
            "            and video_id = #{params.videoId}\n" +
            "        </if>\n" +
            "        <if test=\"params.goodType!=null\">\n" +
            "            and good_type = #{params.goodType}\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<Good> page);

    /**
     * 取消点赞
     * @param id
     */
    @Delete("delete from cl_good where good_id = #{id}")
    void deleteById(Integer id);
}
