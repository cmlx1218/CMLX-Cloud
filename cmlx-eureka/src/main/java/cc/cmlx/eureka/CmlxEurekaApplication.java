package cc.cmlx.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CmlxEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmlxEurekaApplication.class, args);
        System.out.println("CMLX-Eureka服务注册中心启动成功");
    }

}
