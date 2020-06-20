package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.entity.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GroupMapper {

    @Insert("insert into `cl_group`(group_name,group_states) values(#{groupName},#{groupStates})")
    int save(Group group);

    @Select("<script>" +
            "select * from cl_group" +
            "<where> " +
            " <if test=\"groupName!=null and groupName!='null'\"> " +
            "and group_name like  CONCAT('%', #{groupName}, '%')" +
            "</if> " +
            "</where> " +
            "</script>")
    List<Group> findAllByPage(@Param("groupName") String groupName);

    @Update("UPDATE `cl_group` SET group_states = 0 WHERE group_id = #{id}")
    void disableClasses(int id);

    @Update("UPDATE `cl_group` SET group_states = 1 WHERE group_id = #{id}")
    void enableClasses(int id);

    @Delete("DELETE FROM cl_group WHERE group_id = #{id}")
    void deleteClassesById(int id);

    @Select("select * from cl_group WHERE group_id = #{id}")
    Group findClassById(int id);

    @Select("select * from cl_group")
    List<Group> findAll();

    @Update("UPDATE `cl_group` SET group_name = #{groupName} WHERE group_id = #{groupId}")
    void alter(Group group);
}
