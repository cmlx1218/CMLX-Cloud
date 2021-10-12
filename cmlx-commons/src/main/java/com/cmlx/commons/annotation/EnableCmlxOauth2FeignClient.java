package com.cmlx.commons.annotation;

import com.cmlx.commons.config.CmlxOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 10:43
 * @Desc ->
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CmlxOAuth2FeignConfigure.class)
public @interface EnableCmlxOauth2FeignClient {



}
