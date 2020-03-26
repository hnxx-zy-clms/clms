/**
 * @FileName: ConnectionMapper
 * @Author: code-fusheng
 * @Date: 2020/3/26 11:13
 * Description: 收藏Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Collection;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

}
