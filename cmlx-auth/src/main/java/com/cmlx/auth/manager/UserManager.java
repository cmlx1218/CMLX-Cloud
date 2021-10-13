package com.cmlx.auth.manager;

import com.cmlx.auth.mapper.MenuMapper;
import com.cmlx.auth.mapper.UserMapper;
import com.cmlx.commons.entity.system.Menu;
import com.cmlx.commons.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 17:31
 * @Desc ->
 **/
@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

    // 返回如：user:add,user:update,user:delete
    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
        //List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        //
        //List<String> perms = new ArrayList<>();
        //for (Menu m: userPermissions){
        //    perms.add(m.getPerms());
        //}
        //return StringUtils.join(perms, ",");
    }
}