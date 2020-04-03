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
}
