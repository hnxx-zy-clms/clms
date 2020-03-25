/**
 * @FileName: GoodMapper
 * @Author: code-fusheng
 * @Date: 2020/3/25 14:33
 * Description: 点赞mapper 数据访问层
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Good;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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
}
