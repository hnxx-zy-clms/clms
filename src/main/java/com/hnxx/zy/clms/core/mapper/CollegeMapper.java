package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.College;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CollegeMapper {

    @Insert("insert into `cl_college`(college_name,college_states) values(#{collegeName},#{collegeStates})")
    int save(College college);

    @Select("select * from cl_college")
    List<College> findAllByPage();

    @Update("UPDATE `cl_college` SET college_states = 0 WHERE college_id = #{id}")
    void disableClasses(int id);

    @Update("UPDATE `cl_college` SET college_states = 1 WHERE college_id = #{id}")
    void enableClasses(int id);
}
