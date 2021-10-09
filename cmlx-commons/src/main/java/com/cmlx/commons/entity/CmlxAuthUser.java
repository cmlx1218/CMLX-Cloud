package com.cmlx.commons.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 16:59
 * @Desc ->
 **/
@Data
public class CmlxAuthUser implements Serializable {

    private static final long serialVersionUID = -7166267596515308846L;
    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

}
