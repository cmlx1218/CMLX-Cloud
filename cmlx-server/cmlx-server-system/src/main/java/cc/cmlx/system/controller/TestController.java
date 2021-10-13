package cc.cmlx.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 12:21
 * @Desc ->
 **/
@Slf4j
@RestController
public class TestController {

    @GetMapping("info")
    public String test() {
        return "febs-server-system";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name) {
        log.info("hello" + name);
        log.error("hello" + name);
        return "hello" + name;
    }

}
