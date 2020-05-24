/**
 * @FileName: ArticleMapper
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:27
 * Description: 文章Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.ArticleStatistics;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ArticleMapper {

    /**
     * 保存
     *
     * @param article
     */
    @Insert("insert into cl_article(article_title, article_author, article_image, article_desc, article_content, article_type, article_source) " +
            "values (#{articleTitle}, #{articleAuthor}, #{articleImage}, #{articleDesc}, #{articleContent}, #{articleType}, #{articleSource})")
    void save(Article article);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Select("select a.*, t.type_name from cl_article a left join cl_article_type t on a.article_type = t.type_id  where a.article_id = #{id} and a.is_deleted = 0")
    Article getById(Integer id);

    /**
     * 更新
     *
     * @param article
     */
    @Update("<script>update cl_article set\n" +
            "        version = version + 1\n" +
            "        <if test=\"articleTitle!=null and articleTitle!=''\">\n" +
            "            ,article_title = #{articleTitle}\n" +
            "        </if>\n" +
            "        <if test=\"articleAuthor!=null and articleAuthor!=''\">\n" +
            "            ,article_author = #{articleAuthor}\n" +
            "        </if>\n" +
            "        <if test=\"articleImage!=null and articleImage!=''\">\n" +
            "            ,article_image = #{articleImage}\n" +
            "        </if>\n" +
            "        <if test=\"articleDesc!=null and articleDesc!=''\">\n" +
            "            ,article_desc = #{articleDesc}\n" +
            "        </if>\n" +
            "        <if test=\"articleContent!=null and articleContent!=''\">\n" +
            "            ,article_content = #{articleContent}\n" +
            "        </if>\n" +
            "        <if test=\"articleGood!=null\">\n" +
            "            ,article_good = #{articleGood}\n" +
            "        </if>\n" +
            "        <if test=\"articleRead!=null\">\n" +
            "            ,article_read = #{articleRead}\n" +
            "        </if>\n" +
            "        <if test=\"articleCollection!=null\">\n" +
            "            ,article_collection = #{articleCollection}\n" +
            "        </if>\n" +
            "        <if test=\"articleType!=null\">\n" +
            "            ,article_type = #{articleType}\n" +
            "        </if>\n" +
            "        <if test=\"articleComment!=null\">\n" +
            "            ,article_comment = #{articleComment}\n" +
            "        </if>\n" +
            "        <if test=\"articleSource!=null and articleSource!=''\">\n" +
            "            ,article_source = #{articleSource}\n" +
            "        </if>\n" +
            "        where article_id = #{articleId}\n" +
            "        and version = #{version}</script>")
    void update(Article article);

    /**
     * 根据id删除
     *
     * @param id
     */
    @Update("update cl_article set is_deleted = 1 where article_id = #{id}")
    void deleteById(Integer id);



    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select a.*, t.type_name from cl_article a left join cl_article_type t on a.article_type = t.type_id \n" +
            "        where a.is_deleted = 0 \n" +
            "        <if test=\"params.articleTitle!=null and params.articleTitle!=''\">\n" +
            "            and a.article_title like CONCAT('%', #{params.articleTitle}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.articleType!=null\">\n" +
            "            and a.article_type = #{params.articleType}\n" +
            "        </if>\n" +
            "        <if test=\"params.articleAuthor!=null and params.articleAuthor!=''\">\n" +
            "            and a.article_author = #{params.articleAuthor}\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Article> getByPage(Page<Article> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select count(*) from cl_article a left join cl_article_type t on a.article_type = t.type_id\n" +
            "        where a.is_deleted = 0 \n" +
            "        <if test=\"params.articleTitle!=null and params.articleTitle!=''\">\n" +
            "            and a.article_title like CONCAT('%', #{params.articleTitle}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"params.articleType!=null\">\n" +
            "            and a.article_type = #{params.articleType}\n" +
            "        </if>\n" +
            "        <if test=\"params.articleAuthor!=null and params.articleAuthor!=''\">\n" +
            "            and a.article_author = #{params.articleAuthor}\n" +
            "        </if>\n" +
            "</script>")
    int getCountByPage(Page<Article> page);

    /**
     * 改变文章状态
     * @param article
     */
    @Update("update cl_article set version = version + 1, is_enabled = #{isEnabled} where article_id = #{articleId} and version = #{version}")
    void updateEnable(Article article);

    /**
     * 阅读 +1
     * @param id
     */
    @Update("update cl_article set article_read = article_read + 1 where article_id = #{id}")
    void addRead(Integer id);

    /**
     * 更新文章的收藏量
     * @param cCount
     * @param aid
     */
    @Update("update cl_article set article_collection = #{cCount} where article_id = #{aid} and is_deleted = 0")
    void updateCollectionCount(int cCount, int aid);

    /**
     * 查询用户文章排行
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select article_author articleAuthor, " +
            "        count(article_author) articleCounts," +
            "        sum(article_read) readCounts," +
            "        sum(article_good) goodCounts," +
            "        sum(article_comment) commentCounts," +
            "        sum(article_collection) collectionCounts" +
            "        from cl_article\n" +
            "        where is_deleted = 0 \n" +
            "        group by article_author \n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<Map> getArticleCountInfo(Page<ArticleStatistics> page);

    /**
     * 获取用户文章类型信息
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select t.type_name typeName, " +
            "        count(a.article_type) articleCounts" +
            "        from cl_article a left join cl_article_type t on a.article_type = t.type_id\n" +
            "        where a.is_deleted = 0 and a.article_author = #{params.articleAuthor}\n" +
            "        group by a.article_type \n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by count(a.${sortColumn}) ${sortMethod}\n" +
            "        </if>\n" +
            "</script>")
    List<Map> getUserArticleCountInfo(Page<ArticleStatistics> page);
}
