package com.cmlx.auth.properties;

import lombok.Data;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 15:41
 * @Desc ->
 **/
@Data
public class CmlxClientsProperties {

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";

}
