package com.cmlx.commons.annotation;

import com.cmlx.commons.config.CmlxAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 17:20
 * @Desc -> 驱动 {@link CmlxAuthExceptionConfigure} 配置类
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CmlxAuthExceptionConfigure.class)
public @interface EnableCmlxAuthExceptionHandler {

}
