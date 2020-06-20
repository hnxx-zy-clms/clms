package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.College;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CollegeMapper {

    @Insert("insert into `cl_college`(college_name,college_states) values(#{collegeName},#{collegeStates})")
    int save(College college);

    @Select("<script>" +
            "select * from cl_college" +
            "<where> " +
            " <if test=\"collegeName!=null and collegeName!='null'\"> " +
            "and college_name like  CONCAT('%', #{collegeName}, '%')" +
            "</if> " +
            "</where> " +
            "</script>")
    List<College> findAllByPage(@Param("collegeName")String collegeName);

    @Update("UPDATE `cl_college` SET college_states = 0 WHERE college_id = #{id}")
    void disableClasses(int id);

    @Update("UPDATE `cl_college` SET college_states = 1 WHERE college_id = #{id}")
    void enableClasses(int id);

    @Delete("DELETE FROM cl_college WHERE college_id = #{id}")
    void deleteClassesById(int id);

    @Select("select * from cl_college")
    List<College> findAll();

    @Select("select * from cl_college WHERE college_id = #{id}")
    College findClassById(int id);

    @Update("UPDATE `cl_college` SET college_name = #{collegeName} WHERE college_id = #{collegeId}")
    void alter(College college);
}
