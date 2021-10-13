package cc.cmlx.system.config;

import cc.cmlx.system.properties.CmlxServerSystemProperties;
import com.cmlx.commons.handler.CmlxAccessDeniedHandler;
import com.cmlx.commons.handler.CmlxAuthExceptionEntryPoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 12:15
 * @Desc -> 所有访问CMLX-ServerSystem的请求都需要认证，只有通过认证服务器发放的令牌才能进行访问
 **/
@Configuration
@EnableResourceServer
public class CmlxServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private CmlxAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CmlxAuthExceptionEntryPoint authExceptionEntryPoint;
    @Autowired
    private CmlxServerSystemProperties properties;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(authExceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

}
