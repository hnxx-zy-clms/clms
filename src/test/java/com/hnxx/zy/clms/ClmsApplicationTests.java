package com.hnxx.zy.clms;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.service.ArticleService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ClmsApplicationTests {

    @Autowired
    private ArticleService articleService;

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
        CreateIndexRequest request = new CreateIndexRequest("clms_article");
        //2.客户端执行创建请求 IndicesClient,请求后获得响应
        CreateIndexResponse createIndexResponse =
                client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    //测试获取索引 判断其是否存在
    @Test
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("clms_article");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    //删除索引
    @Test
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("clms_article");
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

}
