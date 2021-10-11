package cc.cmlx.test.config;

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
 * @Date -> 2021/10/11 14:59
 * @Desc ->
 **/
@Configuration
@EnableResourceServer
public class CmlxServerTestResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private CmlxAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CmlxAuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
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