package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.GithubCount;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.security.test.entity.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/5/9 9:50
 */

@Mapper
@Repository
public interface UserMapper {

    /**
     * *
     * 从数据拿出用户信息
     *
     * @param username
     * @return
     */
    @Select("select user_id,user_name,user_password,user_position_id from cl_user where user_name=#{username}")
    SysUser selectByName(String username);

    /**
     * 获取总人数
     *
     * @return
     */
    @Select("select count(*) from cl_user")
    int selectUserNum();

    /**
     * 获取用户Id
     *
     * @param name
     * @return
     */
    @Select("select user_id from cl_user where user_name = #{name}")
    int selectUserId(@Param("name") String name);

    /**
     * 获取用户姓名
     *
     * @param id
     * @return
     */
    @Select("select user_name from cl_user where user_id = #{id}")
    String selectUserName(@Param("id") Integer id);

    /**
     * 获取人数
     *
     * @param page
     * @return
     */
    @Select({"<script> \n" +
            "select count(*) from cl_user where user_status = 1 \n" +
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
     *
     * @param page
     * @return
     */
    @Select({"<script> \n" +
            "select DISTINCT(user_group_id) from cl_user where user_status = 1 \n" +
            "and user_classes_id = #{params.userClassesId}\n" +
            "</script>"})
    Integer[] getGroupIds(Page<ReportStatistics> page);

    /**
     * 根据名字查询
     *
     * @param name
     * @return
     */
    @Select("select * from cl_user where name=#{name}")
    List<User> getByName(String name);

    /**
     * 分页查询用户
     *
     * @return
     */
    @Select("<script>" +
            "        select user_id, user_name, created_time, update_time, is_enabled from cl_user\n" +
            "        where is_deleted = 0 \n" +
            "        <if test=\"params.userName!=null and params.userName!=''\">\n" +
            "            and user_name like CONCAT('%', #{params.userName}, '%')\n" +
            "        </if>\n" +
            "        <if test=\"sortColumn!=null and sortColumn!=''\">\n" +
            "            order by ${sortColumn} ${sortMethod}\n" +
            "        </if>\n" +
            "        limit #{index}, #{pageSize}" +
            "</script>")
    List<User> getUserByPage();

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Insert("<script>" +
            "        insert into cl_user(user_id,user_name,user_password,created_time,updated_time)\n" +
            "        values(#{userId},#{userName},#{userPassword},#{createdTime},#{updatedTime})\n"+
            "</script>")
    void insertUser(User user);

    /**
     * 根据ID删除用户
     *
     * @param id
     */
    @Delete("delete from cl_user where id=#{id}")
    void deleteUserById(int id);

    /**
     * 更新用户信息
     *
     * @param user
     */
    @Update("update cl_user set name=#{name},age=#{age},updated_time=#{updatedTime} where id=#{id}")
    void updateUserById(User user);
}


