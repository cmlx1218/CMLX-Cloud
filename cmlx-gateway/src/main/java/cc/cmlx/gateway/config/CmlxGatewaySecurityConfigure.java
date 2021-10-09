package cc.cmlx.gateway.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 18:37
 * @Desc -> 因为引入了cmlx-common模块，包含了Spring Cloud Security依赖，所以我们需要定义一个自己的WebSecurity配置类，来覆盖默认的。这里主要是关闭了csrf功能，否则会报csrf相关异常
 **/
@EnableWebSecurity
public class CmlxGatewaySecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
