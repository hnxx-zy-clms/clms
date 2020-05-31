package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.common.utils.SearchPage;
import com.hnxx.zy.clms.core.service.SearchService;
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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @FileName: SearchServiceImpl
 * @Author: code-fusheng
 * @Date: 2020/5/26 12:41
 * @version: 1.0
 * Description:
 */

@Service
public class SearchServiceImpl implements SearchService {

    //面向对象操作
    @Autowired
    @Qualifier("restHighLevelClient")  //如定义的名称与配置文件一直则不需要这个
    private RestHighLevelClient client;

    @Override
    public SearchResponse searchPageHighlight(SearchPage searchPage) throws IOException {
        // if(searchPage.getPageNo() <= 1){
        //     searchPage.setPageNo(1);
        // }
        // 1、创建查询索引
        SearchRequest searchRequest = new SearchRequest(searchPage.getIndex());
        // 2、条件查询
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 3、分页
        sourceBuilder.from(searchPage.getPageNo());
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
        return searchResponse;
    }
}
