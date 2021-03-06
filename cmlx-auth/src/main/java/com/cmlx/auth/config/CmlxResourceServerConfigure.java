package com.cmlx.auth.config;

import com.cmlx.commons.handler.CmlxAccessDeniedHandler;
import com.cmlx.commons.handler.CmlxAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 16:32
 * @Desc -> 优先级高于CmlxSecurityConfigure order = 3
 * 用于处理非/oauth/开头的请求，其主要用于资源的保护，
 * 客户端只能通过OAuth2协议发放的令牌来从资源服务器中获取受保护的资源
 **/
@Configuration
//开启资源服务器相关配置
@EnableResourceServer
public class CmlxResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private CmlxAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CmlxAuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //表明该安全配置对所有请求都生效
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(authExceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
