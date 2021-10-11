package com.cmlx.commons.config;

import com.cmlx.commons.handler.CmlxAccessDeniedHandler;
import com.cmlx.commons.handler.CmlxAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 17:01
 * @Desc ->
 **/
public class CmlxAuthExceptionConfigure {

    @Bean
    // 当IOC容器中没有指定名称或类型的Bean的时候，就注册它
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public CmlxAccessDeniedHandler accessDeniedHandler() {
        return new CmlxAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public CmlxAuthExceptionEntryPoint authenticationEntryPoint() {
        return new CmlxAuthExceptionEntryPoint();
    }

}
