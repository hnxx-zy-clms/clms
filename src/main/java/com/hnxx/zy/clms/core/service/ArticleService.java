/**
 * @FileName: ArticleService
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:17
 * Description: 文章业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.ArticleStatistics;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ArticleService {
    /**
     * 保存，添加
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
    Page<Article> getByPage(Page<Article> page);

    /**
     * 根据id启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 根据id弃用
     * @param id
     */
    void disableById(Integer id);

    /**
     * 根据id阅读 增加阅读量
     * @param id
     * @return
     */
    Article readById(Integer id);

    /**
     * 文章信息数据统计
     * @param page
     * @return
     */
    Page<ArticleStatistics> getArticleCountInfo(Page<ArticleStatistics> page);

    /**
     * 获取用户各类型文章数
     * @param page
     * @return
     */
    Page<ArticleStatistics> getUserArticleCountInfo(Page<ArticleStatistics> page);

    /**
     * 高亮搜索
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     */
    SearchResponse searchPageHighlightBuilder(String keyword, int pageNo, int pageSize) throws IOException;
}
