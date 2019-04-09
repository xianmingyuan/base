package com.example.base.distribution.lock.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributionLock {

    String path() default "";

}
