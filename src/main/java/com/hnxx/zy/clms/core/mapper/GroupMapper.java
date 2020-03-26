package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.College;
import com.hnxx.zy.clms.core.entity.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GroupMapper {

    @Insert("insert into `cl_group`(group_name,group_states) values(#{groupName},#{groupStates})")
    int save(Group group);

    @Update("UPDATE `cl_group` SET group_states = 0 WHERE group_id = #{id}")
    int updateClasses(int id);

    @Select("select * from cl_group")
    List<Group> findAllByPage();
}
