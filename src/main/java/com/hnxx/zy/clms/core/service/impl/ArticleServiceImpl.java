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
import com.hnxx.zy.clms.core.entity.ArticleStatistics;
import com.hnxx.zy.clms.core.entity.Comment;
import com.hnxx.zy.clms.core.mapper.ArticleMapper;
import com.hnxx.zy.clms.core.mapper.ArticleTypeMapper;
import com.hnxx.zy.clms.core.mapper.CommentMapper;
import com.hnxx.zy.clms.core.service.ArticleService;
import com.hnxx.zy.clms.security.test.services.UserService;
import org.apache.poi.ss.formula.functions.T;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
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
        articleMapper.update(article);
        int tid = article.getArticleType();
        int aCount = articleTypeMapper.getArticleCountByType(tid);
        articleTypeMapper.updateArticleCount(tid, aCount);
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article readById(Integer id) {
        // read +1
        articleMapper.addRead(id);
        // 获取文章实体 根据id
        Article article = articleMapper.getById(id);
        // 获取文章评论量
        // int commentCount1 = commentMapper.getCountByAid(id);
        // article.setArticleComment(commentCount1);
        // // 获取文章下的所有文章评论
        // List<Comment> commentList = commentMapper.getCommentByAid(id);
        // for(Comment comment1 : commentList){
        //     // 获取文章评论的id 这个id是评论的评论的pid
        //     int pid = comment1.getCommentId();
        //     // 根据 pid 获取评论的评论量
        //     int commentCount2 = commentMapper.getCountByCid(pid);
        //     // 设置评论的评论量
        //     comment1.setCommentCount(commentCount2);
        //     // 通过文章的评论id获取评论的评论list
        //     List<Comment> comments = commentMapper.getCommentByPid(pid);
        //     // 遍历评论下的评论
        //     for (Comment comment2 : comments) {
        //         // 将评论下的评论添加到评论的评论集合
        //         comment1.getCommentList().add(comment2);
        //     }
        //     // 将文章的评论添加到文章的评论列表
        //     article.getCommentList().add(comment1);
        // }
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

    @Override
    public SearchResponse searchPageHighlightBuilder(String keyword, int pageNo, int pageSize) throws IOException {
        if(pageNo <= 1){ pageNo=1; }
        // 1、创建查询索引
        SearchRequest searchRequest = new SearchRequest("article_index");
        // 2、条件查询
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 3、分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);
        // 4、精准匹配(中文)
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("articleTitle", keyword);
        sourceBuilder.query(matchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 5、高亮设置(替换返回结果文本中目标值的文本内容)
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("articleTitle");
        highlightBuilder.requireFieldMatch(true);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);
        // 6、执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse;
    }
}
