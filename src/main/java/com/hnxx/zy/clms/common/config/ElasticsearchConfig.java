package com.hnxx.zy.clms.common.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @FileName: ElasticsearchClientConfig
 * @Author: code-fusheng
 * @Date: 2020/5/14 16:05
 * @version: 1.0
 * Description: Elasticsearch配置类
 */

@Configuration
public class ElasticsearchConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("120.92.150.229", 9200, "http")
                )
        );
        return client;
    }
}
