package com.cmlx.commons.config;

import com.cmlx.commons.interceptor.CmlxServerProtectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 14:21
 * @Desc -> 让过滤器 {@link com.cmlx.commons.interceptor.CmlxServerProtectInterceptor } 生效，通过配置类将其注册到 IOC 容器
 **/
@Configuration
public class CmlxServerProtectConfigure implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor cmlxServerProtectInterceptor() {
        return new CmlxServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cmlxServerProtectInterceptor());
    }
}
