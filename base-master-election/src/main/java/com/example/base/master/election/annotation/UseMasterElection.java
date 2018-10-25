package com.example.base.master.election.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface UseMasterElection {

    /**
     * 唯一、需要以/开头，符合地址格式
     */
    String path();

    /**
     * 任意值，尚无使用场景
     */
    String value();

}
