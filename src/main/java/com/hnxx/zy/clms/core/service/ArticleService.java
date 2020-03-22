/**
 * @FileName: ArticleService
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:17
 * Description: 文章业务逻辑接口
 */
package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.core.entity.Article;

public interface ArticleService {
    /**
     * 保存，添加
     * @param article
     */
    void save(Article article);
}
