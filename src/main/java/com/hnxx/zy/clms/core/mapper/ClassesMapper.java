package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.ClassSex;
import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.ClassesReport;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClassesMapper {

    @Insert("insert into `cl_classes`(classes_name,classes_states,classes_college_id) values(#{classesName},#{classesStates},#{classesCollegeId})")
    int save(Classes classes);

    @Update("UPDATE `cl_classes` SET classes_states = 0 WHERE classes_id = #{id}")
    int disableClasses(int id);

    @Update("UPDATE `cl_classes` SET classes_states = 1 WHERE classes_id = #{id}")
    int enableClasses(int id);

    @Select("<script>" +
            "SELECT c.classes_id,c.classes_name,c.classes_states,p.college_name " +
            "FROM cl_classes c " +
            "LEFT JOIN cl_college p " +
            "ON c.classes_college_id = p.college_id" +
            "<where> " +
            " <if test=\"name!=null and name!='null'\"> " +
            "and classes_name like  CONCAT('%', #{name}, '%')" +
            "</if> " +
            "</where> " +
            "</script>")
    List<Classes> findAllClassesByPage(@Param("name") String name);

    @Select("SELECT cl_classes.classes_name item , COUNT(*) count, count(*)/(SELECT COUNT(*) FROM cl_user) percent " +
            "FROM cl_user " +
            "LEFT JOIN cl_classes " +
            "ON cl_user.user_classes_id = cl_classes.classes_id " +
            "WHERE cl_user.is_enabled = 1 AND cl_user.is_deleted=0 AND cl_classes.classes_states=1 " +
            "GROUP BY cl_classes.classes_id")
    List<ClassesReport> report();

    @Select("SELECT sex, count(*) /(select count(*) from cl_user) as percent\n" +
            "FROM cl_user\n" +
            "group by sex")
    List<ClassSex> findSexPercent();

    @Delete("DELETE FROM cl_classes WHERE classes_id = #{id}")
    void deleteClassesById(@Param("id") int id);

    @Select("SELECT * from cl_classes where classes_id = #{id}")
    Classes findClassById(@Param("id") int id);

    @Select("SELECT * from cl_classes")
    List<Classes> findAll();

    @Update("UPDATE `cl_classes` SET classes_name = #{classesName} WHERE classes_id = #{classesId}")
    void alter(Classes classes);
}
