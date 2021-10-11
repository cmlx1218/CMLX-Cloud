package cc.cmlx.test;

import com.cmlx.commons.annotation.EnableCmlxAuthExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient
@SpringBootApplication
// 开启注册 CmlxAccessDeniedHandler和CmlxAuthExceptionEntryPoint
@EnableCmlxAuthExceptionHandler
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CmlxServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmlxServerTestApplication.class, args);
    }

}
