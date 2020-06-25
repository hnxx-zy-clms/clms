/**
 * @FileName: ConnectionMapper
 * @Author: code-fusheng
 * @Date: 2020/3/26 11:13
 * Description: 收藏Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Collection;
import com.hnxx.zy.clms.core.entity.Good;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionMapper {

    /**
     * 收藏文章 文章收藏数 +1
     * @param aid
     */
    @Update("update cl_article set article_collection = article_collection + 1 where article_id = #{aid}")
    void collectionArticle(int aid);

    /**
     * 收藏提问 文章收藏数 +1
     * @param qid
     */
    @Update("update cl_question set question_collection = question_collection + 1 where question_id = #{qid}")
    void collectionQuestion(int qid);

    /**
     * 收藏视频 视频收藏数 +1
     * @param vid
     */
    @Update("update cl_video set collect = collect + 1 where video_id = #{vid}")
    void collectionVideo(int vid);

    /**
     * 添加收藏
     * @param collection
     */
    @Insert("insert into cl_collection(user_id, article_id, question_id, video_id, collection_type) values(#{userId}, #{articleId}, #{questionId}, #{videoId}, #{collectionType})")
    void save(Collection collection);

    /**
     * 根据用户id和文章id查询文章收藏信息集合
     * @param uid
     * @param aid
     * @return
     */
    @Select("select * from cl_collection where user_id = #{userId} and article_id = #{articleId}")
    List<Collection> getCollectionCountForArticle(@Param("userId") Integer uid, @Param("articleId") Integer aid);

    /**
     * 根据用户id和提问id查询提问收藏信息集合
     * @param uid
     * @param qid
     * @return
     */
    @Select("select * from cl_collection where user_id = #{userId} and question_id = #{questionId}")
    List<Collection> getCollectionCountForQuestion(@Param("userId") Integer uid, @Param("questionId") Integer qid);

    /**
     * 根据用户id和视频id查询视频收藏信息集合
     * @param uid
     * @param vid
     * @return
     */
    @Select("select * from cl_collection where user_id = #{userId} and video_id = #{videoId}")
    List<Collection> getCollectionCountForVideo(@Param("userId") Integer uid, @Param("videoId") Integer vid);

    /**
     * 因为这些是历史记录,所以使用逻辑删除
     * @param id
     */
    @Update("update cl_collection set is_deleted = 1 where collection_id = #{id} ")
    void delete(Integer id);

    /**
     * 根据文章id获取文章的收藏量
     * @param aid
     * @return
     */
    @Select("select count(*) from cl_collection where article_id = #{aid}")
    int getCollectionCountByAid(int aid);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from cl_collection where collection_id = #{id} and is_deleted = 0")
    Collection getById(Integer id);

    /**
     * 根据用户id和文章id查询文章是否收藏
     * @param uid
     * @param aid
     * @return
     */
    @Select("select * from cl_collection a where a.user_id = ${uid} and a.article_id = ${aid} and a.is_deleted = 0")
    List<Collection> getCollectionCount(@Param("uid") Integer uid,@Param("aid") Integer aid);

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select c.*, a.article_title, q.question_description from cl_collection c left join cl_article a on c.article_id = a.article_id left join cl_question q on c.question_id = q.question_id\n" +
            "        where c.is_deleted = 0 \n" +
            "        <if test=\"params.userId!=null\">\n" +
            "            and c.user_id = #{params.userId}\n" +
            "        </if>\n" +
            "        <if test=\"params.collectionType!=null\">\n" +
            "            and collection_type = #{params.collectionType}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Collection> getByPage(Page<Collection> page);

    /**
     * 统计总数
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select count(*) from cl_collection c left join cl_article a on c.article_id = a.article_id\n" +
            "        where c.is_deleted = 0 \n" +
            "        <if test=\"params.userId!=null\">\n" +
            "            and c.user_id = #{params.userId}\n" +
            "        </if>\n" +
            "        <if test=\"params.collectionType!=null\">\n" +
            "            and collection_type = #{params.collectionType}\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<Collection> page);

    /**
     * 根据用户id查询收藏列表 后台管理
     * @param id
     * @return
     */
    @Select("select * from cl_collection where user_id = #{id}")
    List<Collection> getListByUserId(Integer id);


}
