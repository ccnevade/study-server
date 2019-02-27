package com.fat.cc.core.exception;

import com.fat.cc.core.review.ApiReview;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Created by cc on 18/5/14
 * 统一异常处理
 */
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ApiReview serverError(Exception e) {
        e.printStackTrace();
        return ApiReview.fail(e.getMessage());
    }
}
