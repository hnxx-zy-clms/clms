package com.hnxx.zy.clms;

import javafx.print.Collation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动类
 * @Author: code-fusheng
 * @Data: 2020/3/17 13:09  --- 2020/3/21 20:30  code-fusheng 完成了项目的基本构建
 */

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.hnxx.zy.clms.core.mapper")
@EnableCaching
@EnableScheduling
public class ClmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClmsApplication.class, args);
    }

}
