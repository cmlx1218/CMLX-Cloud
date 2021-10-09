package com.cmlx.auth.config;

import com.cmlx.auth.service.CmlxUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 16:28
 * @Desc -> 和Web相关的安全配置 @Order(100)，设置为@Order(2)，使/oauth/开头的请求由CmlxSecurityConfigure过滤器链处理，剩下的其他请求由CmlxResourceServerConfigure过滤器链处理。
 * 用于处理/oauth开头的请求，Spring Cloud OAuth内部定义的获取令牌，
 * 刷新令牌的请求地址都是以/oauth/开头的，也就是说CmlxSecurityConfigure用于处理和令牌相关的请求
 **/
@Order(2)
// 开启和Web相关的安全配置
@EnableWebSecurity
public class CmlxSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private CmlxUserDetailService userDetailService;

    /**
     * 定义了几个和密码加密校验相关的方法
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 对于一个相同的密码，每次加密出来的加密串都不同
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // FebsSecurityConfigure安全配置类只对/oauth/开头的请求有效
        http.requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

}
