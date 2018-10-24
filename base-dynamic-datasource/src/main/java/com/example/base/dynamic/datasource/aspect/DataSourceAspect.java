package com.example.base.dynamic.datasource.aspect;

import com.example.base.dynamic.datasource.annotation.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author xianmingyuan
 */
@Slf4j
@Aspect
@Component
public class DataSourceAspect {

    @Around("@annotation(dynamicDataSource))")
    public Object proceed(ProceedingJoinPoint jp, DynamicDataSource dynamicDataSource) {
        try {
            String name = dynamicDataSource.name();
            com.example.base.dynamic.datasource.DynamicDataSource.setName(name);
            jp.proceed();
        } catch (Throwable throwable) {
            log.error("切换数据源后执行异常", throwable);
        }
        return null;
    }

}
