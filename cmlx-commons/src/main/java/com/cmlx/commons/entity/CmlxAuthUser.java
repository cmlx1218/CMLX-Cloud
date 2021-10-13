package com.cmlx.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 16:59
 * @Desc ->
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class CmlxAuthUser extends User {

    private Long userId;

    private String avatar;

    private String email;

    private String mobile;

    private String sex;

    private Long deptId;

    private String deptName;

    private String roleId;

    private String roleName;

    private Date lastLoginTime;

    private String description;

    private String status;

    public CmlxAuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CmlxAuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
