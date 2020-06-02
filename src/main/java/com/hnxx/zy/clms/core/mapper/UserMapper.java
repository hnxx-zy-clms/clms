package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.common.utils.Page;
import com.hnxx.zy.clms.core.entity.ReportStatistics;
import com.hnxx.zy.clms.core.entity.User;
import com.hnxx.zy.clms.core.entity.UserSearch;
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
    @Select("select * from cl_user where user_name=#{username} or mobile = #{username}")
    User selectByName(String username);

    /**
     * *
     * 从数据拿出用户信息
     *
     * @param userId
     * @return
     */
    @Select("select * from cl_user where user_id=#{userId}")
    User selectById(Integer userId);

    /**
     * 获取总人数
     *
     * @return
     */
    @Select("select count(*) from cl_user where is_enabled = 1 and is_deleted = 0 and user_position_id in (1,2)")
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
            "select count(*) from cl_user where is_enabled = 1 and is_deleted = 0 \n" +
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
            "select DISTINCT(user_group_id) from cl_user where is_enabled = 1 and is_deleted = 0 \n" +
            "and user_classes_id = #{params.userClassesId}\n" +
            "</script>"})
    Integer[] getGroupIds(Page<ReportStatistics> page);

    /**
     * 查询用户详细信息(分页)
     * 四表左连接查询
     *
     * @return
     */
    @Select("<script>" +
            "       select a.*, b.group_name, c.classes_name, d.college_name\n" +
            "       from (cl_user as a left join cl_group as b\n" +
            "       on a.user_group_id = b.group_id\n" +
            "       left join cl_classes as c on a.user_classes_id = c.classes_id\n" +
            "       left join cl_college as d on a.user_college_id = d.college_id)\n" +
            "       group by a.user_id asc" +
            "</script>")
    List<UserSearch> getUserByPage();

    /**
     * 根据id查询用户信息
     * 包括弃用的和删除的
     *
     * @param id
     * @return
     */
    @Select("<script>" +
            "       select a.*, b.group_name, c.classes_name, d.college_name\n" +
            "       from (cl_user as a left join cl_group as b\n" +
            "       on a.user_group_id = b.group_id\n" +
            "       left join cl_classes as c on a.user_classes_id = c.classes_id\n" +
            "       left join cl_college as d on a.user_college_id = d.college_id)\n" +
            "       where a.user_id = #{id}\n" +
            "</script>")
    UserSearch getById(Integer id);

    /**
     * 根据ID删除用户
     *
     * @param id
     */
    @Update("update cl_user set is_deleted = 1 where user_id = #{id}")
    void deleteOneById(Integer id);

    /**
     * 更新用户操作时间
     *
     * @param id
     */
    @Update("update cl_user set updated_time=#{updatedTime} where user_id=#{id}")
    void updateTimeById(Integer id);

    /**
     * 用户更新头像
     *
     * @param userId
     * @param userIcon
     */
    @Update("update cl_user set user_icon = #{userIcon} where user_id = #{userId}")
    void updateUserIconById(Integer userId, String userIcon);

    /**
     * 根据电话号码取用户信息
     *
     * @param mobile
     * @return
     */
    @Select("SELECT * FROM cl_user WHERE mobile= #{mobile}")
    User selectByMobile(String mobile);

    /**
     * 根据用户id查询组Id
     *
     * @param id
     * @return
     */
    @Select("select user_group_id from cl_user where user_id = #{userId}")
    String selectByGroupId(Integer id);

    /**
     * 根据用户id或者用户名查询用户信息
     * 四表左连接查询
     *
     * @param user
     * @return
     */
    @Select("<script>" +
            "       select a.*, b.group_name, c.classes_name, d.college_name\n" +
            "       from (cl_user as a left join cl_group as b\n" +
            "       on a.user_group_id = b.group_id\n" +
            "       left join cl_classes as c on a.user_classes_id = c.classes_id\n" +
            "       left join cl_college as d on a.user_college_id = d.college_id)\n" +
            "       where (a.user_id = #{userId} or a.user_name = #{userName} )and is_deleted = 0\n" +
            "       group by a.user_id asc" +
            "</script>")
    List<UserSearch> getByGroup(User user);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @Insert("<script>" +
            "insert into cl_user(user_id, user_name, user_password, mobile, sex, name, user_college_id,\n" +
            "                   user_classes_id, user_group_id, user_icon, user_description,\n" +
            "                   created_time, updated_time, user_position_id, is_enabled, is_deleted)\n" +
            "         values(#{userId}, #{userName}, #{userPassword}, #{mobile}, #{sex}, #{name},\n" +
            "                #{userCollegeId},#{userClassesId}, #{userGroupId}, #{userIcon}, #{userDescription},\n" +
            "                #{createdTime}, #{updatedTime}, #{userPositionId}, #{isEnabled}, #{isDeleted})" +
            "</script>")
    void insertUser(UserSearch user);

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Update("<script>" +
            "       update cl_user\n" +
            "       set user_id = #{userId},\n" +
            "           user_name = #{userName},\n" +
            "           user_password = #{userPassword},\n" +
            "           mobile = #{mobile},\n" +
            "           sex = #{sex},\n" +
            "           name = #{name},\n" +
            "           user_college_id = #{userCollegeId},\n" +
            "           user_classes_id = #{userClassesId},\n" +
            "           user_group_id = #{userGroupId},\n" +
            "           user_icon = #{userIcon},\n" +
            "           user_description = #{userDescription},\n" +
            "           created_time = #{createdTime},\n" +
            "           updated_time = #{updatedTime},\n" +
            "           user_position_id = #{userPositionId},\n" +
            "           is_enabled = #{isEnabled},\n" +
            "           is_deleted = #{isDeleted}\n" +
            "           where user_id = #{userId}\n" +
            "</script>")
    void updateById(UserSearch user);

    /**
     * 启用
     * @param id
     */
    @Update("update cl_user set is_enabled = 1 where user_id = #{id}")
    void updateEnable(Integer id);

    /**
     * 弃用
     * @param id
     */
    @Update("update cl_user set is_enabled = 0 where user_id = #{id}")
    void updateDisable(Integer id);
}
