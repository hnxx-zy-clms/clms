package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 南街北巷
 * @data 2020/6/3 3:55
 */
@Mapper
@Repository
public interface RoleMapper {

    /**
     * 分页查询
     *
     * @return
     */
    @Select("select * from cl_role group by role_id")
    List<Role> getAllRole();

    /**
     * 增加角色
     *
     * @return
     */
    @Insert("<script>" +
            "       insert into cl_role(role_id, role_name, role_position,\n" +
            "                   role_position_id, role_description, created_time,\n" +
            "                   updated_time, is_enabled, is_deleted)\n" +
            "       values(#{roleId}, #{roleName}, #{rolePosition}, #{rolePositionId},\n" +
            "               #{roleDescription}, #{createdTime}, #{updatedTime},\n" +
            "               #{isEnabled}, #{isDeleted})" +
            "</script>")
    void insertRole(Role role);

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    @Select("select * from cl_role where is_deleted = '0'")
    Role getById(Integer id);
}
