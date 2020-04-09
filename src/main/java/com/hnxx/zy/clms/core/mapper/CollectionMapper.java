/**
 * @FileName: ConnectionMapper
 * @Author: code-fusheng
 * @Date: 2020/3/26 11:13
 * Description: 收藏Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Collection;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CollectionMapper {



    /**
     * 添加收藏
     * @param collection
     */
    @Insert("insert into cl_collection(article_id, user_id) values(#{articleId}, #{userId})")
    void save(Collection collection);

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
     * 分页查询
     * @param page
     * @return
     */
    @Select("<script>" +
            "        select c.*, a.article_title from cl_collection c left join cl_article a on c.article_id = a.article_id\n" +
            "        where c.is_deleted = 0 \n" +
            "        <if test=\"params.userId!=null\">\n" +
            "            and c.user_id = #{params.userId}\n" +
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
    @Select("select count(*) from cl_collection where is_deleted = 0")
    int getCountByPage(Page<Collection> page);

    /**
     * 根据用户id查询收藏列表 前台
     * @param id
     * @return
     */
    @Select("select c.*, a.article_title from cl_collection c left join cl_article a on c.article_id = a.article_id where user_id = #{id} and c.is_deleted = 0")
    List<Collection> getListByUserId(Integer id);
}
