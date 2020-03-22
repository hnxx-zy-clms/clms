/**
 * @FileName: ArticleServcieImpl
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:17
 * Description: 文章业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.mapper.ArticleMapper;
import com.hnxx.zy.clms.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void save(Article article) {
        articleMapper.save(article);
    }
}
