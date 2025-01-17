package com.langrentao.antmall.common.advice;

import com.langrentao.antmall.common.entity.ResultWrapper;
import com.langrentao.antmall.common.exception.BusinessException;
import com.langrentao.antmall.common.util.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ResultWrapper<Object> handleException(Exception e) throws Exception {
        log.error(e.getMessage(), e);

        // 如果某个自定义异常有@ResponseStatus注解，就继续抛出
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        // 实际项目中应该这样写，防止用户看到详细的异常信息
        // return ResultWrapper.error().message.message("操作失败");
        return ResultWrapper.error().message(e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResultWrapper<Object> handleBusinessException(Exception e) throws Exception {
        log.error(e.getMessage(), e);

        // 如果某个自定义异常有@ResponseStatus注解，就继续抛出
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        return ResultWrapper.error().message(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResultWrapper<?> handleNullPointerException(NullPointerException e) {
        log.error(e.getMessage(), e);

        // 如果某个自定义异常有@ResponseStatus注解，就继续抛出
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        String message = ThrowableUtil.getLastStackTrace(e, null);
        return ResultWrapper.error().message(message);
    }
}