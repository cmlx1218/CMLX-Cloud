package cc.cmlx.test.controller;

import cc.cmlx.test.service.IHelloService;
import com.cmlx.commons.annotation.EnableCmlxOauth2FeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 15:00
 * @Desc ->
 **/
@RestController
// 让该配置类CmlxOAuth2FeignConfigure生效
@EnableCmlxOauth2FeignClient
public class TestController {

    @Autowired
    private IHelloService helloService;

    @GetMapping("test1")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String test1() {
        return "拥有'user:add'权限";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public String test2() {
        return "拥有'user:update'权限";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name) {
        return this.helloService.hello(name);
    }

}
