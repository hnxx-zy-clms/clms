package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.entity.Position;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PositionMapper {

    @Insert("insert into `cl_position`(position_name,position_status) values(#{positionName},#{positionStatus})")
    int save(Position position);

    @Select("<script>" +
            "select * from cl_position" +
            "<where> " +
            " <if test=\"positionName!=null and positionName!='null'\"> " +
            "and position_name like  CONCAT('%', #{positionName}, '%')" +
            "</if> " +
            "</where> " +
            "</script>")
    List<Position> findAllByPage(@Param("positionName") String positionName);

    @Update("UPDATE `cl_position` SET position_Status = 0 WHERE position_Id = #{id}")
    int disableClasses(int id);

    @Update("UPDATE `cl_position` SET position_Status = 1 WHERE position_Id = #{id}")
    int enableClasses(int id);

    @Delete("DELETE FROM cl_position WHERE position_id = #{id}")
    void deleteClassesById(int id);

    @Select("select * from cl_position WHERE position_id = #{id}")
    Position findClassById(int id);

    @Select("select * from cl_position")
    List<Position> findAll();

    @Update("UPDATE `cl_position` SET position_name = #{positionName} WHERE position_id = #{positionId}")
    void alter(Position position);
}
