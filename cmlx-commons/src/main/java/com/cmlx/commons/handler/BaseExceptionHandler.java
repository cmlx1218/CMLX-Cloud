package com.cmlx.commons.handler;

import com.cmlx.commons.entity.CmlxResponse;
import com.cmlx.commons.exception.CmlxAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 18:01
 * @Desc -> 全局异常处理，对于通用的异常类型捕获可以在BaseExceptionHandler中定义，而当前微服务系统独有的异常类型捕获可以在GlobalExceptionHandler中定义
 **/
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CmlxResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new CmlxResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = CmlxAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CmlxResponse handleFebsAuthException(CmlxAuthException e) {
        log.error("系统错误", e);
        return new CmlxResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public CmlxResponse handleAccessDeniedException() {
        return new CmlxResponse().message("没有权限访问该资源");
    }

}
