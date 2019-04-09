package com.example.base.distribution.scheduling.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributionScheduled {

    String path() default "";

}
