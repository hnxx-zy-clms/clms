package com.hnxx.zy.clms.core.mapper;

import com.hnxx.zy.clms.core.entity.Classes;
import com.hnxx.zy.clms.core.entity.Group;
import com.hnxx.zy.clms.core.entity.Position;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PositionMapper {

    @Insert("insert into `cl_position`(position_name,position_status) values(#{positionName},#{positionStatus})")
    int save(Position position);

    @Update("UPDATE `cl_position` SET positionStatus = 0 WHERE positionId = #{id}")
    int updateClasses(int id);

    @Select("select * from cl_position")
    List<Position> findAllByPage();

}
