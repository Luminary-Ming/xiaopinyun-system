package com.xiaopinyun.exception;

import com.xiaopinyun.bean.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常
 */
// @RestControllerAdvice 是 Spring 框架中一个用于统一处理控制器异常和返回结果的注解
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(BizException.class)
    public Result<String> handlerBizException() {
        return Result.unkown();
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<String> handlerRuntimeException() {
        return Result.unkown();
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handlerException() {
        return Result.unkown();
    }
}
