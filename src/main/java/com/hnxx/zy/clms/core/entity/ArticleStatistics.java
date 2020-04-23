/**
 * @FileName: ArticleStatistics
 * @Author: code-fusheng
 * @Date: 2020/4/17 12:20
 * Description: 文章数据统计实体
 */
package com.hnxx.zy.clms.core.entity;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ArticleStatistics {

    /**
     * 数据统计模型名称
     */
    private String name;

    /**
     * 数据统计数量
     */
    public Map<String, Integer> countMap = new HashMap<>(16);

    /**
     * 百分比
     */
    private Double percent;




}
