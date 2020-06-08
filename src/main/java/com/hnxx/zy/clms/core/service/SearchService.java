package com.hnxx.zy.clms.core.service;

import com.hnxx.zy.clms.common.utils.SearchPage;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

/**
 * @FileName: SearchService
 * @Author: code-fusheng
 * @Date: 2020/5/26 12:39
 * @version: 1.0
 * Description:
 */

public interface SearchService {
    /**
     * 基础高亮搜索
     * @return
     * @throws IOException
     */
    SearchResponse searchPageHighlight(SearchPage searchPage) throws IOException;

    /**
     * 基础高亮 + 多字段
     * @param searchPage
     * @throws IOException
     * @return
     */
    SearchResponse searchPageHighlightWithFields(SearchPage searchPage) throws IOException;
}
