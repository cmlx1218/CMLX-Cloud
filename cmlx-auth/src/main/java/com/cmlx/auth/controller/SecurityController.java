package com.cmlx.auth.controller;

import com.cmlx.commons.entity.CmlxResponse;
import com.cmlx.commons.exception.CmlxAuthException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 17:05
 * @Desc ->
 **/
@RestController
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public CmlxResponse signout(HttpServletRequest request) throws CmlxAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        CmlxResponse cmlxResponse = new CmlxResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new CmlxAuthException("退出登录失败");
        }
        return cmlxResponse.message("退出登录成功");
    }

}
