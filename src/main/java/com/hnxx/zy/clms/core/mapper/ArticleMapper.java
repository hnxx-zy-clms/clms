/**
 * @FileName: ArticleMapper
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:27
 * Description: 文章Mapper
 */
package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleMapper {

    /**
     * 保存
     * @param article
     */
    void save(Article article);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Article getById(Integer id);

    /**
     * 更新
     * @param article
     */
    void update(Article article);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<Article> getByPage(Page<Article> page);

    /**
     * 查询总数
     * @param page
     * @return
     */
    int getCountByPage(Page<Article> page);

    /**
     * 改变文章状态
     * @param article
     */
    void updateEnable(Article article);
}
