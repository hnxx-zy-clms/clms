package com.hnxx.zy.clms.controller;

import com.alibaba.fastjson.JSON;
import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.SearchPage;
import com.hnxx.zy.clms.core.entity.Article;
import com.hnxx.zy.clms.core.service.SearchService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @FileName: SearchController
 * @Author: code-fusheng
 * @Date: 2020/5/26 12:54
 * @version: 1.0
 * Description:
 */

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * 高亮搜索
     * @return
     * @throws IOException
     */
    @PostMapping("/baseSearch")
    public Result<SearchPage<Object>> baseSearch(@RequestBody SearchPage searchPage) throws IOException {
        SearchResponse searchResponse = searchService.searchPageHighlight(searchPage);
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
        searchPage.setList(list);
        return new Result<>(searchPage);
    }
}
