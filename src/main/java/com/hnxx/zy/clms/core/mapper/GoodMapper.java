/**
 * @FileName: GoodMapper
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:33
 * Description: 点赞mapper 数据访问层
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Good;
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
     * 保存点赞
     * @param good
     */
    @Insert("insert into cl_good(good_id, user_id, article_id, comment_id) values (#{goodId}, #{userId}, #{articleId}, #{commentId})")
    void save(Good good);

    /**
     * 根据用户id查询点赞信息集合
     * @param id
     * @return
     */
    @Select("select * from cl_good where user_id = #{id}")
    List<Good> getListByUserId(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
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
            "</script>")
    int getCountByPage(Page<Good> page);

    /**
     * 取消点赞
     * @param id
     */
    @Delete("delete from cl_good where good_id = #{id}")
    void deleteById(Integer id);
}
