package com.hnxx.zy.clms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 * @Author: fusheng
 * @Data: 2020/3/17 13:09  ---  14:56   完成了项目的基本构建
 * @Data: 提交测试
 */

@SpringBootApplication
@MapperScan("com.hnxx.zy.clms.core.mapper")
public class ClmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClmsApplication.class, args);
    }

}
