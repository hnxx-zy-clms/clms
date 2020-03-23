package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.security.test.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @program: clms
 * @description: 测试securityMapper
 * @author: nile
 * @create: 2020-03-22 22:17
 **/
@Mapper
public interface UserMapper {

    /**
     * 从数据拿出用户信息
     */
    @Select("select * from sysuser where username=#{username}")
    SysUser selectByName(String username);

}

