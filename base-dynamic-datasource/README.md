## 1.在启动类开启动态数据源功能

    @EnableDynamicDataSource
## 2.配置多数据源

    base:
      dynamic:
        datasource:
          write:
            driver-class-name: com.mysql.jdbc.Driver
            jdbc-url: jdbc:mysql://111.231.249.159:3306/master?characterEncoding=utf8&useSSL=false
            username: root
            password: root
          read:
            driver-class-name: com.mysql.jdbc.Driver
            jdbc-url: jdbc:mysql://111.231.249.159:3306/slave?characterEncoding=utf8&useSSL=false
            username: root
            password: root
    
## 2.在业务方法指定使用的数据源

    @DynamicDataSource(name = "write")
    
# 注意

    1.目前不支持分布式事务
    2.调用同类方法要想使用不同的数据源，需要注入动态代理对象self，通过self调用