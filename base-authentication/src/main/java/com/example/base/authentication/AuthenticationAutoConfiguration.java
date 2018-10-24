package com.example.base.authentication;

import com.example.base.authentication.aspect.AuthenticationAspect;
import com.example.base.authentication.feign.AuthenticationRequestInterceptor;
import com.example.base.authentication.rest.AuthenticationClientHttpRequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xianmingyuan
 */
@Slf4j
@Configuration
@ComponentScan
public class AuthenticationAutoConfiguration {

    public AuthenticationAutoConfiguration(){
        log.info("启动鉴权组件，相关问题请查看鉴权文档。");
    }

    @Bean
    public AuthenticationProperties authenticationProperties(){
        return new AuthenticationProperties();
    }

    @Bean
    public AuthenticationOncePerRequestFilter authenticationOncePerRequestFilter(AuthenticationProperties properties){
        return new AuthenticationOncePerRequestFilter(properties);
    }

    @Bean
    public AuthenticationRequestInterceptor authenticationRequestInterceptor(AuthenticationProperties properties) {
        return new AuthenticationRequestInterceptor(properties);
    }

    @Bean
    public AuthenticationAspect authenticationAspect(AuthenticationProperties properties){
        return new AuthenticationAspect(properties);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(AuthenticationProperties properties) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new AuthenticationClientHttpRequestInterceptor(properties));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }


}
