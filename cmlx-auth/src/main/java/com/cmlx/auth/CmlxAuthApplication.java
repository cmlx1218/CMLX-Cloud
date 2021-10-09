package com.cmlx.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CmlxAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmlxAuthApplication.class, args);
        System.out.println("CMLX-Cloud 认证中心启动成功");
    }

}
