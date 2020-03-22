package com.hnxx.zy.clms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动类
 * @Author: fusheng
 * @Data: 2020/3/17 13:09  --- 2020/3/21 20:30  code-fusheng 完成了项目的基本构建
 * @Data: 2020/3/21 21:34  --- 2020/3/21 22:21  code-fusheng 完成了 Article 实体类编写
 * @Data: 2020/3/22 10:43  --- 2020/3/22 19:28  code-fusheng 完成了 ArticleType 相关操作的编写(待完善)
 */

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.hnxx.zy.clms.core.mapper")
public class ClmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClmsApplication.class, args);
    }

}
