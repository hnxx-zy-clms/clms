package com.hnxx.zy.clms.common.utils;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Question;
import com.hnxx.zy.clms.core.service.ArticleService;
import com.hnxx.zy.clms.core.service.QuestionService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @FileName: SearchUtils
 * @Author: code-fusheng
 * @Date: 2020/5/28 0:16
 * @version: 1.0
 * Description: 搜索工具类
 * cron表达式语法
 * [秒] [分] [小时] [日] [月] [周] [年]
 * 注：[年]不是必须的域，可以省略[年]，则一共6个域
 */

@Component
public class SearchUtils {

    @Resource
    private ArticleService articleService;

    @Resource
    private QuestionService questionService;

    /**
     * 面向对象操作
     * 如定义的名称与配置文件一直则不需要这个
     */
    @Resource
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    /**
     * 批量处理文章数据(全量同步) 每天凌晨1点执行一次
     * @throws IOException
     */
    @Scheduled(cron = "0 0 1 * * ?")
    void articleFullUpdate() throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout("10s");
        // 获取数据库的article数据
        List<Article> articleList = articleService.getList();
        for(Article article : articleList) {
            request.add(
                    new IndexRequest("clms_article_index")
                            .id(""+article.getArticleId())
                            .source(JSON.toJSONString(article), XContentType.JSON)
            );
        }
        // 客户端执行请求
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        // 返回是否失败状态
        System.out.println(responses.hasFailures());
    }

    /**
     * 批量处理文章数据(全量同步) 每天凌晨1点执行一次
     * @throws IOException
     */
    @Scheduled(cron = "0 0 1 * * ?")
    void questionFullUpdate() throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout("10s");
        // 获取数据库的article数据
        List<Question> questionList = questionService.getList();
        for(Question question : questionList) {
            request.add(
                    new IndexRequest("clms_article_index")
                            .id(""+question.getQuestionId())
                            .source(JSON.toJSONString(question), XContentType.JSON)
            );
        }
        // 客户端执行请求
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        // 返回是否失败状态
        System.out.println(responses.hasFailures());
    }

}
