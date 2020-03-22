## 【班务管理系统】

#### 文件目录结构 [定期更新] date:2020/3/22 10:28
```
│  pom.xml
│  README.md
├─logs
│      blog-all.log
│      blog-debug.log
│      blog-error.log
│      blog-info.log
│      blog-warn.log
├─sql
│      cl_article.sql
│      cl_log.sql
│
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─hnxx
    │  │          └─zy
    │  │              └─clms
    │  │                  │  ClmsApplication.java               // 项目启动类
    │  │                  ├─common
    │  │                  │  ├─aspect
    │  │                  │  │      RequestAspect.java          // AOP 切面
    │  │                  │  │
    │  │                  │  ├─config
    │  │                  │  │      XxxConfig.java              // 配置类模版
    │  │                  │  │
    │  │                  │  ├─enums
    │  │                  │  │      ResultEnum.java             // 返回结果枚举
    │  │                  │  │      StateEnum.java              // 状态码枚举
    │  │                  │  │
    │  │                  │  ├─exception
    │  │                  │  │      ClmsException.java          // 统一异常处理（不会）
    │  │                  │  │
    │  │                  │  └─utils
    │  │                  │          Page.java                  // 分页工具类(分页对象)
    │  │                  │          Result.java                // 统一返回结果
    │  │                  │          StringUtils.java           // 增强 StringUtils
    │  │                  │          ThreadLocalContext.java    // 本地线程变量
    │  │                  │          XxxUtils.java              // 工具类模版
    │  │                  │
    │  │                  ├─controller
    │  │                  │      LogController.java             // 日志控制类
    │  │                  │      XxxController.java             // 控制类模版
    │  │                  │
    │  │                  ├─core
    │  │                  │  ├─entity   
    │  │                  │  │      Article.java                // 文章实体类
    │  │                  │  │      Log.java                    // 日志实体类
    │  │                  │  │      Xxx.java                    // 实体类模版
    │  │                  │  │
    │  │                  │  ├─mapper
    │  │                  │  │      LogMapper.java              // logMapper
    │  │                  │  │      XxxMapper.java              // Mapper 模版
    │  │                  │  │
    │  │                  │  └─service
    │  │                  │      │  LogService.java             // log 业务逻辑接口
    │  │                  │      │  XxxService.java             // 业务逻辑接口模版
    │  │                  │      │
    │  │                  │      └─impl
    │  │                  │              LogServiceImpl.java    // log 业务逻辑接口实现类
    │  │                  │              XxxServiceImpl.java    // 业务逻辑接口实现类模版
    │  │                  │
    │  │                  └─security                            
    │  │                          xxxxxxx.java
    │  │
    │  └─resources
    │      │  application.yml   
    │      │  banner.txt                                        // 自定义 banner 图标
    │      │  logback-spring.xml                                // 日志配置文件
    │      │
    │      └─mapper
    │              logMapper.xml
    │
    └─test
        └─java
            └─com
                └─hnxx
                    └─zy
                        └─clms
                            │  ClmsApplicationTests.java
                            │
                            └─core
                                └─service
                                        LogServiceTest.java

```

#### ***PS ： 关于日志和异常处理的相关工功能，大多都是固定写法和模版，不影响常规的业务逻辑接口开发！***         
