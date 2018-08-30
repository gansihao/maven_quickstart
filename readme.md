#项目说明
1. #####数据库
    * weblogic连接池
    * druid连接池
2. #####编码
    * getByte()
    * ISO-8859-1
    * GBK
    * UTF-8
    * UNICODE
3. #####jkd8
    * lambda表达式
4. #####百度AI文字识别
5. #####SpringMVC
    1. ######Annotation(注解)
        * **@Bean** 申明bean
        ``` 
            * PropertySourcesPlaceholderConfigurer 开启占位符
            * MultipartResolver 开启文件上传
            * HttpMessageConverter 解决乱码问题
        ```
        * **@EnableWebMvc** 开启java config
        * **@EnableAspectJAutoProxy** 开启切面
        * **@Aspect** 申明切面
        * **@Configuration** 申明配置类
        * **@ResponseBody** 申明返回字符串
        * **@Profile** 定义环境
        * **@PropertySource** 申明使用配置文件
        * **@ControllerAdvice** 控制器通知，用于全局异常处理
           * **@ExceptionHandler** 申明异常处理方法
    
    2. ######集成Redis缓存
        * **@Cacheable** 申明方法使用缓存
        * **@EnableCaching** 开启缓存
        * **@Bean** CacheManager 申明缓存管理器，用于@Cacheable
        
    3. ######集成Druid
        * **@Bean** DataSource,使用DruidDataSource数据源
        * **@Bean** StatFilter 开启sql状态监控
        * 注册StatViewServlet 开启监控界面
        * 添加WebStatFilter 开启web页面监控
        
    4. ######去配置
        1. 继承WebApplicationInitializer重写onStartup方法可注册Servlet和添加Filter
        2. 使用@Configuration注解类，在类中使用@Bean申明bean
    


 