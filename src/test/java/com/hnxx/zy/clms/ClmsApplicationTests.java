package com.hnxx.zy.clms;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.core.entity.Answer;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.entity.Question;
import com.hnxx.zy.clms.core.service.AnswerService;
import com.hnxx.zy.clms.core.service.ArticleService;
import com.hnxx.zy.clms.core.service.QuestionService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class ClmsApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Test
    void contextLoads() {
    }

    //面向对象操作
    @Autowired
    @Qualifier("restHighLevelClient")  //如定义的名称与配置文件一直则不需要这个
    private RestHighLevelClient client;

    //测试索引的创建
    @Test
    void testCreateIndex() throws IOException {
        //1.创建索引请求   类似于 kibana 中的put命令
        CreateIndexRequest request = new CreateIndexRequest("clms_answer_index");
        //2.客户端执行创建请求 IndicesClient,请求后获得响应
        CreateIndexResponse createIndexResponse =
                client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    //测试获取索引 判断其是否存在
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("clms_article_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //删除索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("clms_article_index");
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());   //判断是否删除成功
    }

    //测试添加文档
    @Test
    void testAddDocument() throws IOException {
        // 1、创建对象
        Article article = articleService.getById(1);
        // 2、创建请求
        IndexRequest request = new IndexRequest("clms_article_index");
        // 3、设置规则 => put /clms_article_index/_doc/1
        request.id("1");
        // 4、设置超时
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("5s");
        // 5、将数据放入请求
        request.source(JSON.toJSONString(article), XContentType.JSON);
        // 6、客户端发送请求。获取响应结果
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);

        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());
    }

    /**
     * 批量处理文章数据(全量同步) 每天凌晨1点执行一次
     * @throws IOException
     */
    @Test
    void articleFullUpdate() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("clms_article_index");
        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
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
     * 批量处理提问数据(全量同步) 每天凌晨1点执行一次
     * @throws IOException
     */
    @Test
    void questionFullUpdate() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("clms_question_index");
        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        BulkRequest request = new BulkRequest();
        request.timeout("10s");
        // 获取数据库的article数据
        List<Question> questionList = questionService.getList();
        for(Question question : questionList) {
            request.add(
                    new IndexRequest("clms_question_index")
                            .id(""+question.getQuestionId())
                            .source(JSON.toJSONString(question), XContentType.JSON)
            );
        }
        // 客户端执行请求
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        // 返回是否失败状态
        System.out.println(responses.hasFailures());
    }

    /**
     * 批量处理提问数据(全量同步) 每天凌晨1点执行一次
     * @throws IOException
     */
    @Test
    void answerFullUpdate() throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("clms_answer_index");
        client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        BulkRequest request = new BulkRequest();
        request.timeout("10s");
        // 获取数据库的article数据
        List<Answer> answerList = answerService.getList();
        for(Answer answer : answerList) {
            request.add(
                    new IndexRequest("clms_answer_index")
                            .id(""+answer.getAnswerId())
                            .source(JSON.toJSONString(answer), XContentType.JSON)
            );
        }
        // 客户端执行请求
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        // 返回是否失败状态
        System.out.println(responses.hasFailures());
    }



    //批量处理数据(全量同步)
    @Test
    void testBulkRequest() throws IOException {
        BulkRequest request = new BulkRequest();
        request.timeout("10s");
        // 获取数据库的article数据
        // List<Article> articleList = articleService.getList();
        // for(Article article : articleList) {
        //     request.add(
        //             new IndexRequest("clms_article_index")
        //                     .id(""+article.getArticleId())
        //                     .source(JSON.toJSONString(article), XContentType.JSON)
        //     );
        // }

        List<Question> questionList = questionService.getList();
        for(Question question : questionList) {
            request.add(
                    new IndexRequest("clms_question_index")
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
