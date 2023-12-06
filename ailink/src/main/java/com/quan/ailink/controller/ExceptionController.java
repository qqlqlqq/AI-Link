package com.quan.ailink.controller;

import com.quan.ailink.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author LQL
 * @Create 2023/12/3 16:03
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.debug("参数报错：{}", e);
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return Result.error(defaultMessage);
    }

    @ExceptionHandler(RuntimeException.class)
    public Result runtimeException(RuntimeException e) {
        log.debug("运行报错：", e.getMessage());
        return Result.error(e.getMessage());
    }
}
