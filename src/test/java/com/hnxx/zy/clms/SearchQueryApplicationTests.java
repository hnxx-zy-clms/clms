package com.hnxx.zy.clms;

import com.hnxx.zy.clms.common.utils.SearchPage;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @FileName: SearchApplicationTests
 * @Author: code-fusheng
 * @Date: 2020/6/7 10:13
 * @version: 1.0
 */

@SpringBootTest
public class SearchQueryApplicationTests {

    @Test
    void contextLoads() {
    }

    //面向对象操作
    @Autowired
    @Qualifier("restHighLevelClient")  //如定义的名称与配置文件一直则不需要这个
    private RestHighLevelClient client;

    /**
     * matchAllQuery 查询
     * 查询所有
     */
    @Test
    void matchAllQuery() throws IOException {
        // 指定索引 可以指定多个
        SearchRequest searchRequest = new SearchRequest("clms_article_index");
        // 创建搜索源构建器（设置分页、高亮、目标字段）
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.source(searchSourceBuilder);
        // 设置分页起始位置
        searchSourceBuilder.from(0);
        // 设置分页大小
        searchSourceBuilder.size(10);
        // 先按 时间createdTime 倒排
        // searchSourceBuilder.sort(SortBuilders.fieldSort("createdTime").order(SortOrder.DESC));
        // 再按 id 正排
        // searchSourceBuilder.sort("articleId");
        // 定义搜索响应
        SearchResponse searchResponse = null;
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(System.out::println);
    }

    /**
     * matchQuery 查询
     */
    @Test
    void matchQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("clms_article_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.source(searchSourceBuilder);
        // MatchQueryBuilder 匹配查询构建器
        // QueryBuilders 查询生成器
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("articleTitle", "测试");
        // 搜索源工厂通过匹配查询构建器的条件执行查询
        searchSourceBuilder.query(matchQueryBuilder);
        // 模糊查询条件
        matchQueryBuilder.fuzziness(Fuzziness.AUTO);
        // 前缀查询的长度
        matchQueryBuilder.prefixLength(3);
        // maxExpansion 选项、用来控制模糊查询
        matchQueryBuilder.maxExpansions(10);

        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        SearchResponse searchResponse = null;
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(System.out::println);
    }

    /**
     * termQuery 项查询
     */
    @Test
    void termQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("clms_article_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.source(searchSourceBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        // termQuery 项查询
        searchSourceBuilder.query(QueryBuilders.termQuery("articleTitle", "测试"));
        SearchResponse searchResponse = null;
        searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        searchResponse.getHits().forEach(System.out::println);
    }

    /**
     * 聚合查询
     */
    @Test
    void aggregation() {
        SearchRequest searchRequest = new SearchRequest("clms_article_index");
        // 略
    }

    @Test
    void searchHighlightBuilder() throws IOException {
        SearchPage searchPage = new SearchPage();
        searchPage.setPageNo(1);
        searchPage.setPageSize(3);
        searchPage.setIndex("clms_article_index");
        searchPage.setKeyword("测试");
        searchPage.setParams("articleTitle");
        String[] fields = {"articleTitle"};
        searchPage.setKeyFields(fields);
        // 1、创建查询索引
        SearchRequest searchRequest = new SearchRequest("clms_article_index");
        // 2、条件查询
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 3、分页
        sourceBuilder.from((searchPage.getPageNo() - 1) * searchPage.getPageSize());
        sourceBuilder.size(searchPage.getPageSize());
        // 4、精准匹配(中文)
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(searchPage.getParams(), searchPage.getKeyword());
        sourceBuilder.query(matchQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 5、高亮设置(替换返回结果文本中目标值的文本内容)
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field(searchPage.getParams());
        highlightBuilder.requireFieldMatch(true);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);
        // 6、执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        // 7、测试输出
        System.out.println(searchResponse);
        //解析结果
        ArrayList<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit doc:searchResponse.getHits().getHits()){
            // 解析高亮字段
            Map<String, HighlightField> highlightFields = doc.getHighlightFields();
            HighlightField paramsTitle = highlightFields.get(searchPage.getParams());
            // 解析原来的结果
            Map<String, Object> sourceAsMap = doc.getSourceAsMap();
            if(paramsTitle!=null){
                Text[] fragments = paramsTitle.fragments();
                String params="";
                for (Text res:fragments){
                    params += res;
                }
                //将原来的替换为高亮的
                sourceAsMap.put(searchPage.getParams(),params);
            }
            list.add(sourceAsMap);
        }
        System.out.println(list);
    }

    @Test
    void searchPageHighlightWithFields() throws IOException{
        SearchPage searchPage = new SearchPage();
        searchPage.setPageNo(1);
        searchPage.setPageSize(3);
        searchPage.setIndex("clms_article_index");
        searchPage.setKeyword("测试");
        String[] fields = {"articleTitle", "articleContent"};
        searchPage.setKeyFields(fields);
        // 1、创建查询索引
        SearchRequest searchRequest = new SearchRequest(searchPage.getIndex());
        // 2、创建搜索源构建器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.source(searchSourceBuilder);
        // 3、多匹配查询构建器
        MultiMatchQueryBuilder multiMatchQueryBuilder = new MultiMatchQueryBuilder(QueryBuilders.multiMatchQuery(searchPage.getKeyFields(), searchPage.getKeyword()));
        // 4、多字段匹配(中文)
        searchSourceBuilder.query(multiMatchQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 5、高亮设置（多字段文本替换）
        // 6、高亮构建器
        // HighlightBuilder highlightBuilder = new HighlightBuilder();
        // // field : 字段
        // for(String field : searchPage.getKeyFields()) {
        //     highlightBuilder.field(field);
        //     highlightBuilder.requireFieldMatch(true);
        //     highlightBuilder.preTags("<span style='color:red'>").postTags("</span>");
        // }
        // searchSourceBuilder.highlighter(highlightBuilder);
        // 7、执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse);
    }


    @Test
    QueryBuilder  buildBasicQueryWithFilter(SearchPage searchPage) {

        String flag = "";
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder shouldQuery = QueryBuilders.boolQuery();
        //如果输入的查询条件为空，则查询所有数据
        if(searchPage.getKeyword() == null || "".equals(searchPage.getKeyword())) {
            queryBuilder.must(QueryBuilders.matchAllQuery());
            return queryBuilder;
        }
        // 基本查询条件
        //queryBuilder.must(QueryBuilders.matchQuery(request.getKeyField(), request.getKey()));
        queryBuilder.must(QueryBuilders.multiMatchQuery(searchPage.getKeyword(), searchPage.getKeyFields()));//多字段查询，字段直接是or的关系
        return queryBuilder;
    }

    void testSearch() {
        SearchPage searchPage = new SearchPage();
        searchPage.setPageNo(1);
        searchPage.setPageSize(3);
        searchPage.setIndex("clms_article_index");
        searchPage.setKeyword("测试");
        String[] fields = {"articleTitle", "articleContent"};
        searchPage.setKeyFields(fields);
        QueryBuilder basicQuery =buildBasicQueryWithFilter(searchPage);
    }



}
