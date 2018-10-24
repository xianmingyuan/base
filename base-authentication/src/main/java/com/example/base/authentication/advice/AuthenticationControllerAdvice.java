package com.example.base.authentication.advice;

import com.example.base.authentication.exception.AuthenticationException;
import com.example.base.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xianmingyuan
 */
@Slf4j
@RestControllerAdvice
public class AuthenticationControllerAdvice {

    @ExceptionHandler(AuthenticationException.class)
    public Response authenticationException(AuthenticationException e) {
        log.info("鉴权失败", e);
        return Response.fail(701, "鉴权失败，请稍后重试");
    }

}
