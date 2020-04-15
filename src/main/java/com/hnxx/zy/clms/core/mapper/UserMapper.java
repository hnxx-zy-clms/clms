package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.security.test.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Select("select user_id,user_name,user_password,user_position_id from cl_user where user_name=#{username}")
    SysUser selectByName(String username);

    /**
     * 获取总人数
     * @return
     */
    @Select("select count(*) from cl_user")
    int selectUserNum();

    /**
     * 获取用户Id
     * @param name
     * @return
     */
    @Select("select user_id from cl_user where user_name = #{name}")
    int selectUserId(@Param("name") String name);

}

