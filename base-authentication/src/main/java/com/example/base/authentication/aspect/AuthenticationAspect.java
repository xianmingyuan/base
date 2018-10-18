package com.example.base.authentication.aspect;

import com.example.base.authentication.annotation.Authentication;
import com.example.base.authentication.AuthenticationProperties;
import com.example.base.authentication.TokenHolder;
import com.example.base.authentication.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Objects;

@Slf4j
@Aspect
public class AuthenticationAspect {

    private AuthenticationProperties properties;

    public AuthenticationAspect(AuthenticationProperties properties) {
        this.properties = properties;
    }

    @Around("@annotation(authentication))")
    public Object authentication(ProceedingJoinPoint jp, Authentication authentication) {
        String token = TokenHolder.getToken();
        log.info("client token [{}], local token [{}]", token, properties.getToken());
        if (!Objects.equals(token, properties.getToken())) {
            log.info("鉴权失败");
            throw new AuthenticationException();
        }
        log.info("鉴权通过");
        try {
            return jp.proceed();
        } catch (Throwable throwable) {
            log.error("鉴权过后执行方法异常");
        }
        return null;
    }

}
