package com.cmlx.auth;

import com.cmlx.commons.annotation.EnableCmlxAuthExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
// 开启注册 CmlxAccessDeniedHandler和CmlxAuthExceptionEntryPoint
@EnableCmlxAuthExceptionHandler
public class CmlxAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmlxAuthApplication.class, args);
        System.out.println("CMLX-Cloud 认证中心启动成功");
    }

}
