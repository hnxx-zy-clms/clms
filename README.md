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
│      clms_test.sql  (3.25) 整体数据库表 (近期无改动)
│      cl_article.sql                       // 文章表 code-fusheng
│      cl_article_collection.sql            // 文章收藏表 code-fusheng
│      cl_article_type.sql                  // 文章类型表 code-fusheng    
│      cl_classes.sql                       //
│      cl_college.sql                       //
│      cl_comment.sql                       // 文章评论表 code-fusheng
│      cl_commission.sql                    //
│      cl_githubuser.sql                    //
│      cl_group.sql                         //
│      cl_log.sql                           //
│      cl_notice.sql                        //
│      cl_notice_user.sql                   //
│      cl_position.sql                      //
│      cl_registration.sql                  //
│      cl_report.sql                        //
│      cl_report_marking.sql                //
│      cl_task.sql                          //
│      cl_task_user.sql                     //
│      cl_user.sql                          //
│      cl_user_report.sql                   //
│      cl_xx.sql                            //
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
    │  │                  │  │      CrosConfig.java             // 跨域请求配置类
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
    │  │                  │      ArticleController.java         // 文章控制类
    │  │                  │      ArticleTypeController.java     // 文章类型控制类
    │  │                  │      CommentController.java         // 评论控制类
    │  │                  │      CommissionController.java      // 代办任务控制类
    │  │                  │      MyErrorController.java         // 全局异常控制类
    │  │                  │      NoticeController.java          // 通知控制类
    │  │                  │      RegistrationController.java    // 签到控制类
    │  │                  │      TaskController.java            // 任务控制类
    │  │                  │      
    │  │                  ├─core
    │  │                  │  ├─entity   
    │  │                  │  │      Article.java                // 文章实体类
    │  │                  │  │      Log.java                    // 日志实体类
    │  │                  │  │      Xxx.java                    // 实体类模版
    │  │                  │  │      ArticleCollection.java      // 文章收藏实体
    │  │                  │  │      ArticleType.java            // 文章类型实体
    │  │                  │  │      Comment.java                // 评论实体
    │  │                  │  │      Commission.java             // 代办实体
    │  │                  │  │      Notice.java                 // 通知实体
    │  │                  │  │      Registration.java           // 签到实体
    │  │                  │  │      Task.java                   // 任务实体
    │  │                  │  │
    │  │                  │  ├─mapper
    │  │                  │  │      LogMapper.java              // logMapper
    │  │                  │  │      XxxMapper.java              // Mapper 模版
    │  │                  │  │      ArticleMapper.java
    │  │                  │  │      ArticleTypeMapper.java
    │  │                  │  │      CommentMapper.java
    │  │                  │  │      CommissionMapper.java
    │  │                  │  │      NoticeMapper.java
    │  │                  │  │      RegistrationMapper.java
    │  │                  │  │      TaskMapper.java
    │  │                  │  │      UserMapper.java
    │  │                  │  │
    │  │                  │  └─service
    │  │                  │      │  LogService.java             // log 业务逻辑接口
    │  │                  │      │  XxxService.java             // 业务逻辑接口模版
    │  │                  │      │  ArticleService.java         // 文章业务逻辑接口
    │  │                  │      │  ArticleTypeService.java     // 文章类型业务逻辑接口
    │  │                  │      │  CommentService.java         // 评论业务逻辑接口
    │  │                  │      │  CommissionService.java      // 代办业务逻辑接口
    │  │                  │      │  NoticeService.java          // 通知业务逻辑接口
    │  │                  │      │  RegistrationService.java    // 签到业务逻辑接口
    │  │                  │      │  TaskService.java            // 任务业务逻辑接口
    │  │                  │      │
    │  │                  │      └─impl
    │  │                  │              LogServiceImpl.java        // log 业务逻辑接口实现类
    │  │                  │              XxxServiceImpl.java        // 业务逻辑接口实现类模版
    │  │                  │              ArticleService.java        // 文章业务逻辑接口实现类
    │  │                  │              ArticleTypeService.java    // 文章类型业务逻辑接口实现类
    │  │                  │              CommentService.java        // 评论业务逻辑接口实现类
    │  │                  │              CommissionService.java     // 代办业务逻辑接口实现类
    │  │                  │              NoticeService.java         // 通知业务逻辑接口实现类
    │  │                  │              RegistrationService.java   // 签到业务逻辑接口实现类
    │  │                  │              TaskService.java           // 任务业务逻辑接口实现类
    │  │                  │
    │  │                  └─security                                               
    │  │                  │  SecurityConfig.class                               //
    │  │                  │
    │  │                  ├─customauths
    │  │                  │      CustomUserDetailsService.class                 // 注册用户类
    │  │                  │
    │  │                  ├─handles
    │  │                  │      CustomAuthenticationFailureHandler.class       //
    │  │                  │      CustomAuthenticationSuccessHandler.class       //
    │  │                  │
    │  │                  ├─jwt
    │  │                  │      JwtAuthTokenFilter.class                       // jwt过滤器
    │  │                  │      JwtProvider.class                              // jwt参数配置
    │  │                  │
    │  │                  └─test
    │  │                      ├─controllers
    │  │                      │      TestController.class                       //
    │  │                      │
    │  │                      ├─entity
    │  │                      │      SysUser.class                              //
    │  │                      │
    │  │                      └─services
    │  │                          │  UserService.class                          //
    │  │                          │
    │  │                          └─Impl
    │  │                                  UserSeviceImpl.class                  //
    │  │

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
