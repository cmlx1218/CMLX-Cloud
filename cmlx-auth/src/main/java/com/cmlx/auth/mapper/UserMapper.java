package com.cmlx.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmlx.commons.entity.system.SystemUser;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 17:28
 * @Desc ->
 **/
public interface UserMapper extends BaseMapper<SystemUser> {
    SystemUser findByName(String username);
}
