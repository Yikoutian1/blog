package com.project.blog.controller;

import com.project.blog.common.Result;
import com.project.blog.exception.CustomException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @ClassName MyExceptionHandler
 * @Description 全局异常
 * @Author QiuLiHang
 * @DATE 2023/3/1 20:16
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionHandler {
    /**
     * 捕获自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    public Result<?> CustomerExceptionHandler(CustomException e){
        log.error("错误原因为："+e.getMessage());
        return Result.error(e.getCode(),e.getMessage());
    }

    // <2> 处理 json 请求体调用接口校验失败抛出的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  Result<?>  methodArgumentNotValidExceptionHandler(HttpServletResponse httpServletResponse, MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String defaultMessage = fieldErrors.get(0).getDefaultMessage();
        return Result.error(defaultMessage);
    }
}
