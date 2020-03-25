package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Classes;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ClassesMapper {

    @Insert("insert into `cl_classes`(classes_name,classes_states) values(#{classesName},#{classesStates})")
    int save(Classes classes);

    @Update("UPDATE `cl_classes` SET classes_states = 0 WHERE classes_id = #{id}")
    int updateClasses(int id);

    @Select("select * from cl_classes")
    List<Classes> findAllClassesByPage();
}
