package com.cmlx.auth.service;

import com.cmlx.auth.manager.UserManager;
import com.cmlx.commons.entity.CmlxAuthUser;
import com.cmlx.commons.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author CMLX
 * @Date -> 2021/10/9 16:55
 * @Desc ->
 **/
@Service
public class CmlxUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserManager userManager;

    /**
     * UserDetails中方法的含义
     * <p>
     * getAuthorities获取用户包含的权限，返回权限集合，权限是一个继承了GrantedAuthority的对象；
     * getPassword和getUsername用于获取密码和用户名；
     * isAccountNonExpired方法返回boolean类型，用于判断账户是否未过期，未过期返回true反之返回false；
     * isAccountNonLocked方法用于判断账户是否未锁定；
     * isCredentialsNonExpired用于判断用户凭证是否没过期，即密码是否未过期；
     * isEnabled方法用于判断用户是否可用。
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = userManager.findByName(username);
        if (systemUser != null) {
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
                notLocked = true;
            CmlxAuthUser authUser = new CmlxAuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            // 两个实体类值的拷贝Spring给我们提供了相应的工具类
            //return transSystemUserToAuthUser(authUser,systemUser);
            BeanUtils.copyProperties(systemUser,authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
        //CmlxAuthUser user = new CmlxAuthUser();
        //user.setUsername(username);
        //user.setPassword(this.passwordEncoder.encode("123456"));
        //
        //return new User(username, user.getPassword(), user.isEnabled(),
        //        user.isAccountNonExpired(), user.isCredentialsNonExpired(),
        //        user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }

    private CmlxAuthUser transSystemUserToAuthUser(CmlxAuthUser authUser, SystemUser systemUser) {
        authUser.setAvatar(systemUser.getAvatar());
        authUser.setDeptId(systemUser.getDeptId());
        authUser.setDeptName(systemUser.getDeptName());
        authUser.setEmail(systemUser.getEmail());
        authUser.setMobile(systemUser.getMobile());
        authUser.setRoleId(systemUser.getRoleId());
        authUser.setRoleName(systemUser.getRoleName());
        authUser.setSex(systemUser.getSex());
        authUser.setUserId(systemUser.getUserId());
        authUser.setLastLoginTime(systemUser.getLastLoginTime());
        authUser.setDescription(systemUser.getDescription());
        authUser.setStatus(systemUser.getStatus());
        return authUser;
    }

}
