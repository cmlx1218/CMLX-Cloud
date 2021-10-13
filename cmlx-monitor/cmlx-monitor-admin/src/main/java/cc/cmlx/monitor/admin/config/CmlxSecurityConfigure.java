package cc.cmlx.monitor.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @Author CMLX
 * @Date -> 2021/10/13 10:56
 * @Desc ->
 **/
@EnableWebSecurity
public class CmlxSecurityConfigure extends WebSecurityConfigurerAdapter {

    //private final String adminContextPath;
    //
    //public CmlxSecurityConfigure(AdminServerProperties adminServerProperties) {
    //    this.adminContextPath = adminServerProperties.getContextPath();
    //}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        //successHandler.setTargetUrlParameter("redirectTo");
        //
        //http.authorizeRequests()
        //        .antMatchers(adminContextPath + "/assets/**").permitAll()
        //        .antMatchers(adminContextPath + "/login").permitAll()
        //        .anyRequest().authenticated()
        //        .and()
        //        .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
        //        .logout().logoutUrl(adminContextPath + "/logout").and()
        //        .httpBasic().and()
        //        .csrf().disable();
    }

}
