package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.GithubCount;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

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

    /**
     * 获取用户姓名
     * @param id
     * @return
     */
    @Select("select user_name from cl_user where user_id = #{id}")
    String selectUserName(@Param("id") Integer id);

    /**
     * 获取人数
     * @return
     */
    @Select({"<script> \n"+
            "select count(*) from cl_user where user_status = 1 \n"+
            "<if test=\" params.userClassesId != null and params.userClassesId  != '' \"  > \n" +
            "and user_classes_id = #{params.userClassesId}\n" +
            "<if test='params.isClasses == 0  and params.userGroupId!=null' > \n" +
            "and user_group_id = #{params.userGroupId}\n" +
            "</if> \n" +
            "</if> \n" +
            "</script>"})
    int getUserNum(Page<ReportStatistics> page);

    /**
     * 获取人数
     * @return
     */
    @Select({"<script> \n"+
            "select DISTINCT(user_group_id) from cl_user where user_status = 1 \n"+
            "and user_classes_id = #{params.userClassesId}\n" +
            "</script>"})
    Integer[] getGroupIds(Page<ReportStatistics> page);

    /**
     * 新增github用户
     * @param githubCount
     */
    @Insert("insert into cl_github_user(name,account_id,token,created_time,update_time)\n" +
            "values(#{name},#{accountId},#{token},#{createdTime},#{updateTime})")
    void insert(GithubCount githubCount);

    /**
     * 删除所有github账号
     * 重置自增Id
     */
    @Delete("truncate table cl_github_user")
    void deleteAllUser();

    /**
     * 根据ID删除github用户
     * @param id
     */
    @Delete("delete from cl_github_user where id=#{id}")
    void deleteUserById(int id);
}

