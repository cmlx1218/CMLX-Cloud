package cc.cmlx.test;

import com.cmlx.commons.annotation.CmlxCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

// 开启feign功能
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
//// 认证类型异常翻译
//@EnableCmlxAuthExceptionHandler
//// 开启带令牌的Feign请求，避免微服务内部调用出现401异常
//@EnableCmlxOauth2FeignClient
//// 开启微服务防护，避免客户端绕过网关直接请求微服务
//@EnableCmlxServerProtect
@CmlxCloudApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CmlxServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmlxServerTestApplication.class, args);
    }

}
