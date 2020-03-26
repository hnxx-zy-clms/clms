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

    @Update("UPDATE `cl_college` SET college_states = 0 WHERE college_id = #{id}")
    int updateClasses(int id);

    @Select("select * from cl_college")
    List<College> findAllByPage();
}
