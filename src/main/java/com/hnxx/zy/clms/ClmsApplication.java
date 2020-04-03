package com.hnxx.zy.clms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动类
 * @Author: code-fusheng
 * @Data: 2020/3/17 13:09  --- 2020/3/21 20:30  code-fusheng 完成了项目的基本构建
 * @Data: 2020/3/21 21:34  --- 2020/3/21 22:21  code-fusheng 完成了文章实体类编写
 * @Data: 2020/3/22 10:43  --- 2020/3/22 19:28  code-fusheng 完成了文章类型相关操作的编写(待完善)
 * @Data: 2020/3/23 12:15  --- 2020/3/23 20:01  code-fusheng 完成了 Mybatis xml 转 注解(新增下划线--驼峰转换配置)
 * @Data: 2020/3/23 11:01  --- 2020/3/24 01:01  peng44 完成了security的基本配置
 * @Data: 2020/3/24 9:34   --- 2020/3/25 01:02  code-fusheng 完成了 评论的开发
 * @Data: 2020/3/25 8:37   --- 2020/3/25 16:09  code-fusheng 完成了点赞功能的开发(功能缺陷)
 * @Date: 2020/3/25 10:30  --- 2020/3/25 19:20  CODEHEN 测试、修改了一些接口的bug
 * @Data: 2020/3/25 6:46   --- 2020/3/25 22:51  code-fusheng 完善了文章模块的功能
 * @Data: 2020/3/25 23:02  --- 2020/3/25 23:43  code-fusheng 修复了点赞和评论的逻辑问题
 */

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.hnxx.zy.clms.core.mapper")
public class ClmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClmsApplication.class, args);
    }

}
