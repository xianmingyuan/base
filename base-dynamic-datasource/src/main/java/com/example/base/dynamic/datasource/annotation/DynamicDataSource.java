package com.example.base.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * 不支持分布式事务
 */
@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicDataSource {

    String name();

}
