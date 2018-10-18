package com.example.base.authentication;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthenticationAutoConfiguration.class)
public @interface EnableAuthentication {
}
