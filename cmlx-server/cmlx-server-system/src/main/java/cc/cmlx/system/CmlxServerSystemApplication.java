package cc.cmlx.system;

import com.cmlx.commons.annotation.EnableCmlxAuthExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient
@SpringBootApplication
// 开启Spring Cloud Security权限注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
// 开启注册 CmlxAccessDeniedHandler和CmlxAuthExceptionEntryPoint
@EnableCmlxAuthExceptionHandler
public class CmlxServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmlxServerSystemApplication.class, args);
        System.out.println("CMLX-Cloud 微服务系统项目启动成功");
    }

}
