package com.cmlx.auth.handler;

import com.cmlx.commons.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 18:03
 * @Desc -> 全局异常处理类，当前服务独有的异常写在这边
 **/
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}