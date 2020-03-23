/**
 * @FileName: ArticleServcieImpl
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:17
 * Description: 文章业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.exception.ClmsException;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.mapper.ArticleMapper;
import com.hnxx.zy.clms.core.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 保存
     * @param article
     */
    @Override
    public void save(Article article) {
        articleMapper.save(article);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Article getById(Integer id) {
        return articleMapper.getById(id);
    }

    /**
     * 更新
     * @param article
     */
    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }

    /**
     * 分页查询
     * @param page
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Article> getByPage(Page<Article> page) {
        // 先查询数据
        List<Article> articleList = articleMapper.getByPage(page);
        page.setList(articleList);
        // 再查询总数
        int totalCount = articleMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    /**
     * 根据id启用
     * @param id
     */
    @Override
    public void enableById(Integer id) {
        Article article = articleMapper.getById(id);
        article.setIsEnabled(StateEnum.ENABLED.getCode());
        articleMapper.updateEnable(article);
    }

    /**
     * 根据id弃用
     * @param id
     */
    @Override
    public void disableById(Integer id) {
        Article article = articleMapper.getById(id);
        article.setIsEnabled(StateEnum.NOT_ENABLE.getCode());
        articleMapper.updateEnable(article);
    }
}
