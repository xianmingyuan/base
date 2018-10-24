package com.example.base.util.advice;

import com.example.base.util.Response;
import com.example.base.util.exception.IdWorkerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xianmingyuan
 */
@Slf4j
@RestControllerAdvice
public class UtilControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public Response illegalArgumentException(IllegalArgumentException e) {
        log.error("参数异常", e);
        return Response.fail(701, "参数异常");
    }

    @ExceptionHandler(IdWorkerException.class)
    public Response idWorkerException(IdWorkerException e) {
        log.info("主键产生工具类异常", e);
        return Response.fail(701, "主键产生异常，请稍后再试");
    }

}
