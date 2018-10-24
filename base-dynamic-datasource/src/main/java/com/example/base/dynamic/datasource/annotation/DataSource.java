package com.example.base.dynamic.datasource.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    String name() default "";

}
