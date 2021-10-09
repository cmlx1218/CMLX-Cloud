package cc.cmlx.eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 16:09
 * @Desc ->
 **/
@EnableWebSecurity
public class cmlxRegisterWebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 由于新版的Spring security 会默认开启防csrf攻击，所有的请求都必须携带crsf这个参数，但是从以上的信息来看显然是没有的。所以我们需要主动去关闭
        //http.csrf().disable();
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
