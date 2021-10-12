package com.cmlx.commons.annotation;

import com.cmlx.commons.config.CmlxServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 14:33
 * @Desc ->
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CmlxServerProtectConfigure.class)
public @interface EnableCmlxServerProtect {
}
