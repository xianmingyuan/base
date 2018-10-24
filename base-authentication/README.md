## 1.在启动类开启鉴权功能

    @EnableAuthentication
    
## 2.配置

    authentication:
        key: authentication
        token: sadfwsadsfde

#注意
    
    1.使用RestTemplate调用服务接口需要使用鉴权模块生成的RestTemplate实例
    2.支持feign接口
