package cc.cmlx.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

// 开启Zuul服务网关功能
@EnableZuulProxy
// 开启服务注册与发现
@EnableDiscoveryClient
@SpringBootApplication
public class CmlxGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmlxGatewayApplication.class, args);
        System.out.println("CMLX-Cloud 网关服务启动成功");
    }

}
