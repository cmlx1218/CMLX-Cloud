package cc.cmlx.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class CmlxMonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmlxMonitorAdminApplication.class, args);
    }

}
