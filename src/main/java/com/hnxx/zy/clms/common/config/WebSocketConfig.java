package com.hnxx.zy.clms.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @FileName: WebSocketConfig
 * @Author: code-fusheng
 * @Date: 2020/6/14 8:03
 * @version: 1.0
 * Description: ws配置类
 */


@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
