## 【班务管理系统】

#### 文件目录结构 [定期更新]
**clms** 项目名(项目根目录)
   * sql 数据库资源文件
   * src 项目资源根目录
       * main
           * java (com.hnxx.zy.clms)
               * [common] 公共层
                   * aspect AOP 切面
                   * config 配置类
                       * XxxConfig : 模版配置类
                   * enums 枚举
                       * ResultEnums : 返回结果枚举
                       * StateEnums : 状态码枚举
                   * exception : 自定义异常处理(尚未使用)
                   * utils 工具类
                       * XxxUtils  : 模版工具类
                       * Result ： 统一返回结果
                       * StringUtils : 增强String工具
                       * ThreadLocalContext : 本地线程全局变量
               * [controller] 控制层
                   * XxxController : 控制类模版
               * [core] 核心层
                   * entity : 实体类
                       * Log : 日志实体类
                       * Xxx : 实体类模版
                   * mapper : 数据访问层接口
                       * LogMapper : 日志数据访问层
                       * XxxMapper ： 数据访问层接口模版
                   * service : 业务逻辑接口
                       * impl ： 业务逻辑接口实现类
                           * LogServiceImpl ： 日志业务逻辑接口实现类
                           * XxxServiceImpl : 业务逻辑接口实现类模版
                       * XxxService ： 业务逻辑接口模版
                       * LogService ：日志业务逻辑接口
               * [security] Security 安全控制层
           * resources
               * application.yml : 配置文件
               * banner.txt ： 自定义banner图标
               * logback-spring.xml : 日志配置文件
   * pom.xml : 项目环境配置文件  
   
   #### ***PS ： 关于日志和异常处理的相关工功能，大多都是固定写法和模版，不影响常规的业务逻辑接口开发！***         
