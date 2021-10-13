package com.cmlx.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cmlx.commons.entity.system.Menu;

import java.util.List;

/**
 * @Author CMLX
 * @Date -> 2021/10/12 17:29
 * @Desc -> 虽然通过继承BaseMapper<T>，我们的Mapper接口已经包含了基础的单表增删改查方法，
 * 但是上面两个接口的方法都需要通过关联数据表的方式查询数据，所以我们需要使用传统的MyBatis XML映射的方式来实现
 **/
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> findUserPermissions(String username);
}
