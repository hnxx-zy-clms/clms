package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.security.test.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @program: clms
 * @description: 测试securityMapper
 * @author: nile
 * @create: 2020-03-22 22:17
 **/
@Mapper
@Repository
public interface UserMapper {

    /**
     * 从数据拿出用户信息
     */
    @Select("select user_name,user_password,user_position_id from cl_user where user_name=#{username}")
    SysUser selectByName(String username);

}

