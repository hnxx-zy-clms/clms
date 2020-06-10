/**
 * @FileName: ArticleServcieImpl
 * @Author: code-fusheng
 * @Date: 2020/3/22 11:17
 * Description: 文章业务逻辑接口实现类
 */
package com.hnxx.zy.clms.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.enums.StateEnum;
import com.hnxx.zy.clms.common.exception.ClmsException;
import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.core.entity.*;
import com.hnxx.zy.clms.core.mapper.ArticleMapper;
import com.hnxx.zy.clms.core.mapper.ArticleTypeMapper;
import com.hnxx.zy.clms.core.mapper.CommentMapper;
import com.hnxx.zy.clms.core.service.ArticleService;
import com.hnxx.zy.clms.core.service.GoodService;
import com.hnxx.zy.clms.core.service.UserService;
import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodService goodService;



    //面向对象操作
    @Autowired
    @Qualifier("restHighLevelClient")  //如定义的名称与配置文件一直则不需要这个
    private RestHighLevelClient client;

    /**
     * 保存
     * @param article
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Article article) {
        articleMapper.save(article);
        int tid = article.getArticleType();
        int aCount = articleTypeMapper.getArticleCountByType(tid);
        articleTypeMapper.updateArticleCount(tid, aCount);
        // 更新Elasticsearch数据
        Article articleDoc = articleMapper.getById(article.getArticleId());
        IndexRequest request = new IndexRequest("clms_article_index");
        request.id(article.getArticleId().toString());
        request.timeout("5s");
        request.source(JSON.toJSONString(articleDoc), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Article getById(Integer id) {
        // 获取文章实体
        Article article = articleMapper.getById(id);
        // 给文章实体的评论总数赋值
        article.setArticleComment(commentMapper.getCountByAid(id));
        return article;
    }

    /**
     * 更新
     * @param article
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Article article) {
        // 获取 article 的新旧分类id
        int oldTypeId = articleMapper.getById(article.getArticleId()).getArticleType();
        int newTypeId = article.getArticleType();
        if(oldTypeId != newTypeId) {
            // 更新旧分类文章数
            ArticleType oldType = articleTypeMapper.getById(oldTypeId);
            oldType.setTypeCount(oldType.getTypeCount() - 1);
            articleTypeMapper.update(oldType);
            // 更新新分类文章数
            ArticleType newType = articleTypeMapper.getById(newTypeId);
            newType.setTypeCount(newType.getTypeCount() - 1);
            articleTypeMapper.update(newType);
        }
        articleMapper.update(article);
        Article articleDoc = articleMapper.getById(article.getArticleId());
        UpdateRequest request = new UpdateRequest("clms_article_index", articleDoc.getArticleId().toString());
        request.timeout("5s");
        request.doc(JSON.toJSONString(articleDoc), XContentType.JSON);
        try {
            client.update(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
        DeleteRequest request = new DeleteRequest("clms_article_index", id.toString());
        request.timeout("5s");
        try {
            client.delete(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Article> getList() {
        return articleMapper.getList();
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
        User user = userService.selectByName(SecurityContextHolder.getContext().getAuthentication().getName());
        int uid = user.getUserId();
        articleList.forEach( article -> {
            int count = goodService.getGoodCountForArticle(uid, article.getArticleId());
            if(count == 0){
                article.setGoodArticleFlag(false);
            }else {
                article.setGoodArticleFlag(true);
            }
        } );
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
        Article articleDoc = articleMapper.getById(id);
        IndexRequest request = new IndexRequest("clms_article_index");
        request.id(articleDoc.getArticleId().toString());
        request.timeout("5s");
        request.source(JSON.toJSONString(articleDoc), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
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
        DeleteRequest request = new DeleteRequest("clms_article_index", id.toString());
        request.timeout("5s");
        try {
            client.delete(request, RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article readById(Integer id) {
        // read +1
        articleMapper.addRead(id);
        // 获取文章实体 根据id
        Article article = articleMapper.getById(id);
        return article;
    }

    @Override
    public Page<ArticleStatistics> getArticleCountInfo(Page<ArticleStatistics> page) {
        // 先查询数据
        List<Map> maps = articleMapper.getArticleCountInfo(page);
        List<ArticleStatistics> asList = new ArrayList<>();
        for(Map m : maps){
            ArticleStatistics as = new ArticleStatistics();
            as.setName((String) m.get("articleAuthor"));
            as.setArticleCounts(Integer.valueOf(m.get("articleCounts").toString()).intValue());
            as.setReadCounts(Integer.valueOf(m.get("readCounts").toString()).intValue());
            as.setGoodCounts(Integer.valueOf(m.get("goodCounts").toString()).intValue());
            as.setCommentCounts(Integer.valueOf(m.get("commentCounts").toString()).intValue());
            as.setCollectionCounts(Integer.valueOf(m.get("collectionCounts").toString()).intValue());
            asList.add(as);
        }
        page.setList(asList);
        // 再查询总数
        int totalCount = asList.size();
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public Page<ArticleStatistics> getUserArticleCountInfo(Page<ArticleStatistics> page) {
        // 先查询数据
        List<Map> maps = articleMapper.getUserArticleCountInfo(page);
        List<ArticleStatistics> asList = new ArrayList<>();
        for(Map m : maps){
            ArticleStatistics as = new ArticleStatistics();
            as.setName((String) m.get("typeName"));
            as.setArticleCounts(Integer.valueOf(m.get("articleCounts").toString()).intValue());
            asList.add(as);
        }
        page.setList(asList);
        // 再查询总数
        int totalCount = asList.size();
        page.setTotalCount(totalCount);
        return page;
    }
}
