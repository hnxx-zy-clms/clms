/**
 * @FileName: ArticleMapper
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:27
 * Description: 文章Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArticleMapper {

    /**
     * 保存
     * @param article
     */
    void save(Article article);
}
