/**
 * @FileName: ArticleStatistics
 * @Author: code-fusheng
 * @Date: 2020/4/17 12:20
 * Description: 文章数据统计实体
 */
package com.hnxx.zy.clms.core.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleStatistics implements Serializable {

    /**
     * 数据统计模型名称
     */
    private String name;

    /**
     * 数据统计数量
     */
    private Integer typeCounts;

    private Integer goodCounts;

    private Integer readCounts;

    private Integer collectionCounts;

    private Integer articleCounts;

    private Integer commentCounts;

    /**
     * 百分比
     */
    private Double percent;




}
