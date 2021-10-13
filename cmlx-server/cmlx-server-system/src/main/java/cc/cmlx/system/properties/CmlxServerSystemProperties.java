package cc.cmlx.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 19:58
 * @Desc ->
 **/
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:cmlx-server-system.properties"})
@ConfigurationProperties(prefix = "cmlx.server.system")
public class CmlxServerSystemProperties {
    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private CmlxSwaggerProperties swagger = new CmlxSwaggerProperties();
}
