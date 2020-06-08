package com.hnxx.zy.clms.controller;

import com.hnxx.zy.clms.common.utils.Result;
import com.hnxx.zy.clms.common.utils.SearchPage;
import com.hnxx.zy.clms.core.service.SearchService;
import net.sf.jsqlparser.statement.select.Distinct;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /**
     * 多字高亮搜索
     * @param searchPage
     * @return
     */
    @PostMapping("/searchHighlightWithFields")
    public Result<SearchPage<Object>> searchHighlightWithFields(@RequestBody SearchPage searchPage) throws IOException {
        SearchResponse searchResponse = searchService.searchPageHighlightWithFields(searchPage);
        // 解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit doc:searchResponse.getHits().getHits()){
            // 解析高亮字段
            Map<String, HighlightField> highlightFields = doc.getHighlightFields();
            for(int i = 0; i < searchPage.getKeyFields().length; i ++) {
                HighlightField fieldTitle = highlightFields.get(searchPage.getKeyFields()[i]);
                // 获取原来的结果集
                Map<String, Object> sourceAsMap = doc.getSourceAsMap();
                if(fieldTitle != null) {
                    // 获取内容中匹配的片段
                    Text[] fragments = fieldTitle.fragments();
                    // 设置当前的目标字段为空
                    String new_fieldTitle = "";
                    for (Text res : fragments) {
                        new_fieldTitle += res;
                    }
                    // 将原来的结果替换为新结果
                    sourceAsMap.put(searchPage.getKeyFields()[i], new_fieldTitle);
                }
                list.add(sourceAsMap);
            }
        }
        // List 数组去重， 多字段查询高亮解析的时候存在数组重复的情况（优化方法未知！）
        List newList = list.stream().distinct().collect(Collectors.toList());
        searchPage.setList(newList);
        return new Result<>(searchPage);
    }
}
