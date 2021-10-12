package com.cmlx.commons.annotation;

import com.cmlx.commons.selector.CmlxCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 15:02
 * @Desc -> CmlxAuthExceptionConfigure、CmlxOAuth2FeignConfigure和CmlxServerProtectConfigure这三个配置类的组合注解
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CmlxCloudApplicationSelector.class)
public @interface CmlxCloudApplication {


}
