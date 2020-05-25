package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Classes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ClassesMapper {

    @Insert("insert into `cl_classes`(classes_name,classes_states,classes_college_id) values(#{classesName},#{classesStates},#{classesCollegeId})")
    int save(Classes classes);

    @Update("UPDATE `cl_classes` SET classes_states = 0 WHERE classes_id = #{id}")
    int disableClasses(int id);

    @Update("UPDATE `cl_classes` SET classes_states = 1 WHERE classes_id = #{id}")
    int enableClasses(int id);

    @Select("SELECT c.classes_id,c.classes_name,c.classes_states,p.college_name " +
            "FROM cl_classes c " +
            "LEFT JOIN cl_college p " +
            "ON c.classes_college_id = p.college_id")
    List<Classes> findAllClassesByPage();
}
