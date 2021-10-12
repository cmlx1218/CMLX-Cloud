package com.cmlx.commons.config;

import com.cmlx.commons.entity.CmlxConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 10:40
 * @Desc -> 通过SecurityContextHolder从请求上下文中获取了OAuth2AuthenticationDetails类型对象，
 * 并通过该对象获取到了请求令牌，然后在请求模板对象requestTemplate的头部手动将令牌添加上去
 * 因为上面我们在febs-server-test的配置文件里添加了feign.hystrix.enabled:true开启了hystrix熔断机制，要让上面的请求拦截器能够顺利获取到令牌
 **/
public class CmlxOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(CmlxConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(CmlxConstant.ZUUL_TOKEN_HEADER, zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }

}
