package com.cmlx.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 15:43
 * @Desc ->
 **/
@Data
// 实质上为@Component的派生注解，用于将CmlxAuthProperties纳入到IOC容器中
@SpringBootConfiguration
// 指定读取的配置文件路径,@PropertySource并没有兼容yml文件的解析，需要自定有factory
@PropertySource(value = {"classpath:cmlx-auth.yml"}, factory = YamlPropertySourceFactory.class)
// 指定了要读取的属性的统一前缀名称为 cmlx.auth
@ConfigurationProperties(prefix = "cmlx.auth")
public class CmlxAuthProperties {

    private CmlxClientsProperties[] clients = {};
    private int accessTokenValiditySeconds;
    private int refreshTokenValiditySeconds;

}
