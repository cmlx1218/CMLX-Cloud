package com.cmlx.auth.config;

import com.cmlx.auth.service.CmlxUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 16:40
 * @Desc -> 认证服务器相关安全配置类
 **/
@Configuration
@EnableAuthorizationServer
public class CmlxAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private CmlxUserDetailService userDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //@Autowired
    //private DataSource dataSource;

    /**
     * 生产上一般使用数据库用户作为客户端
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
        // 客户端从认证服务器获取令牌的时候，必须使用client_id为febs，client_secret为123456的标识来获取
        .withClient("cmlx")
        .secret(passwordEncoder.encode("cmlx1218"))
        // 该client_id支持password模式获取令牌，并且可以通过refresh_token来获取新的令牌
        .authorizedGrantTypes("password", "refresh_token")
        // 在获取client_id为febs的令牌的时候，scope只能指定为all，否则将获取失败
        .scopes("all");

        // 生产环境做法
        //clients.withClientDetails(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices());
    }

    /**
     * 认证服务器生成的令牌将被存储到Redis中
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        // 开启刷新令牌的支持
        tokenServices.setSupportRefreshToken(true);
        // 令牌有效期 60 * 60 * 24秒
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24);
        // 刷新令牌有效时间为60 * 60 * 24 * 7秒
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }

    //@Bean
    //public ClientDetailsService clientDetails() {
    //    //采用数据库用户
    //    return new JdbcClientDetailsService(dataSource);
    //}
}
