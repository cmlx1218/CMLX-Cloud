package com.cmlx.auth;

import com.cmlx.commons.annotation.EnableCmlxAuthExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
// 开启注册 CmlxAccessDeniedHandler和CmlxAuthExceptionEntryPoint
@EnableCmlxAuthExceptionHandler
// 作用为将路径下的Mapper类都注册到IOC容器中
@MapperScan("com.cmlx.auth.mapper")
public class CmlxAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmlxAuthApplication.class, args);
        System.out.println("CMLX-Cloud 认证中心启动成功");
    }

}
